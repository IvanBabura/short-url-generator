package io.github.ivanbabura.shorturlgenerator.controllers;

import io.github.ivanbabura.shorturlgenerator.dto.Response;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/all_urls")
    public ResponseEntity<List<Url_matching>> get_all() {
        url_matching_List = service.findAll();
        return new ResponseEntity<>(url_matching_List, HttpStatus.OK);
    }

    @GetMapping(value = "/url", params = "originalUrl")
    public ResponseEntity<Url_matching> get_by_originalUrlTtl(@RequestParam() String originalUrl) {
        Url_matching result = service.checkTtlOnFindByOriginalUrl(originalUrl);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/url", params = "shortUrl")
    public ResponseEntity<Url_matching> get_by_shortUrl(@RequestParam() String shortUrl) {
        Url_matching result = service.checkTtlOnFindByShortUrl(shortUrl);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/url", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Url_matching> create(@RequestBody() Url_matching url_matching) {
        url_matching = service.createWithoutSave(url_matching.getOriginalUrl());
        service.save(url_matching);
        return new ResponseEntity<>(url_matching, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/url", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestBody() Url_matching url_matching) {
        Url_matching foundUrl = null;
        if (url_matching.getShortUrl() != null)
            foundUrl = service.findByShortUrl(url_matching.getShortUrl());
        if (foundUrl == null && url_matching.getOriginalUrl() != null) {
            foundUrl = service.findByOriginalUrl(url_matching.getOriginalUrl());
            if (foundUrl == null) {
                Response response = new Response(HttpStatus.NOT_FOUND, "Not found.");
                return new ResponseEntity<>(response, response.getStatus());
            }
        } else {
            Response response = new Response(HttpStatus.BAD_REQUEST, "I don't understand, what ar u try delete.");
            return new ResponseEntity<>(response, response.getStatus());
        }
        service.delete(foundUrl);
        Response response = new Response(HttpStatus.OK, "Deleted.");
        return new ResponseEntity<>(response, response.getStatus());
    }
}