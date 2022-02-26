package io.github.ivanbabura.shorturlgenerator.controllers;

import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MainRestController {
    private final Url_matching_Service service;
    private List<Url_matching> url_matching_List;
    //TODO: It is possible to do caching to this list

    @Autowired
    public MainRestController(Url_matching_Service service) {
        this.service = service;
    }

    @RequestMapping("get_all")
    public ResponseEntity<List<Url_matching>> get_all() {
        url_matching_List = service.findAll();
        return (url_matching_List != null) ?
                new ResponseEntity<>(url_matching_List, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get")
    public ResponseEntity<Url_matching> get_by_originalUrl(@RequestParam() String originalUrl) {
        Url_matching result = service.findByOriginalUrl(originalUrl);
        return (result != null) ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get_by_shortUrl")
    public ResponseEntity<Url_matching> get_by_shortUrl(@RequestParam() String shortUrl) {
        Url_matching result = service.findByShortUrl(shortUrl);
        return (result != null) ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Url_matching> create(@RequestParam() String originalUrl) {
        //TODO: there might be a Check here: is it a url or rubbish?
        if (originalUrl.equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Url_matching result = service.findByOriginalUrl(originalUrl);
        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        result = new Url_matching();
        result.setOriginalUrl(originalUrl);
        result.setShortUrl(service.generateShortUrl());
        service.save(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam() String originalUrl) {
        Url_matching foundUrl = service.findByOriginalUrl(originalUrl);
        if(foundUrl!=null){
            service.delete(foundUrl);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{shortUrlEnding}")
    public RedirectView redirect(HttpServletRequest url, @PathVariable String shortUrlEnding){
        String result = service.findOriginalUrlByShortUrl(url.getRequestURL().toString());
        //So strange the second return, but why not?
        //it redirects to error page from FrontendController
        return(result!=null)?
                new RedirectView(result):
                new RedirectView("/service/error");
    }
}
