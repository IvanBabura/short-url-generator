package io.github.ivanbabura.shorturlgenerator.controllers;

import io.github.ivanbabura.shorturlgenerator.dto.Response;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.exceptions.BadRequestException;
import io.github.ivanbabura.shorturlgenerator.exceptions.NoContentException;
import io.github.ivanbabura.shorturlgenerator.exceptions.NotFoundException;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/service")
public class MainRestController {
    private final Url_matching_Service service;
    private List<Url_matching> url_matching_List;
    //TODO: It is possible to do caching to this list
    //TODO: exceptions in this class isn't good idea. That must be in service.

    @Autowired
    public MainRestController(Url_matching_Service service) {
        this.service = service;
    }

    @RequestMapping("/get_all")
    public ResponseEntity<List<Url_matching>> get_all() {
        url_matching_List = service.findAll();
        if (url_matching_List == null) {
            throw new NoContentException("");
        }
        return new ResponseEntity<>(url_matching_List, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Url_matching> get_by_originalUrl(@RequestParam() String originalUrl) {
        Url_matching result = service.findByOriginalUrl(originalUrl);
        if (result == null) {
            throw new NotFoundException("Not found this originalUrl.");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get_by_shortUrl")
    public ResponseEntity<Url_matching> get_by_shortUrl(@RequestParam() String shortUrl) {
        Url_matching result = service.findByShortUrl(shortUrl);
        if (result == null) {
            throw new NotFoundException("Not found this shortUrl.");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Url_matching> create(@RequestParam() String originalUrl){
        Url_matching result = service.create(originalUrl);
        service.save(result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam() String originalUrl) {
        Url_matching foundUrl = service.findByOriginalUrl(originalUrl);
        if(foundUrl!=null){
            service.delete(foundUrl);
            Response response = new Response(HttpStatus.OK, "Deleted.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            Response response = new Response(HttpStatus.NOT_MODIFIED, "Didn't delete. I don't know it.");
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
            //I don't know why, but spring doesn't want to send JSON with status 304 (NOT_MODIFIED).
            //He send empty body with 304 status.
            //Probably, this is embedded in the 304 status.
        }
    }

    @GetMapping("/{shortUrlEnding}")
    public RedirectView redirect(HttpServletRequest url, @PathVariable String shortUrlEnding){
        String result = service.findOriginalUrlByShortUrl(url.getRequestURL().toString());
        if(result!=null){
            return new RedirectView(result);
        } else{
            throw new BadRequestException("I don't know it!");
        }
    }
}
