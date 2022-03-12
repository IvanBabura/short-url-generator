package io.github.ivanbabura.shorturlgenerator.controllers;

import io.github.ivanbabura.shorturlgenerator.dto.Response;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/service")
public class MainRestController {
    private final Url_matching_Service service;
    private List<Url_matching> url_matching_List;
    //TODO: It is possible to do caching to this list

    @Autowired
    public MainRestController(Url_matching_Service service) {
        this.service = service;
    }

    @RequestMapping("/get_all")
    public ResponseEntity<List<Url_matching>> get_all() {
        url_matching_List = service.findAll();
        return new ResponseEntity<>(url_matching_List, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Url_matching> get_by_originalUrlTtl(@RequestParam() String originalUrl) {
        Url_matching result = service.findByOriginalUrlTtl(originalUrl);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get_by_shortUrl")
    public ResponseEntity<Url_matching> get_by_shortUrl(@RequestParam() String shortUrl) {
        Url_matching result = service.findByShortUrlTtl(shortUrl);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Url_matching> create(@RequestParam() String originalUrl) {
        Url_matching result = service.createWithoutSave(originalUrl);
        service.save(result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam() String originalUrl) {
        Url_matching foundUrl = service.findByOriginalUrl(originalUrl);
        service.delete(foundUrl);
        Response response = new Response(HttpStatus.OK, "Deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
