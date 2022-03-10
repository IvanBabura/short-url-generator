package io.github.ivanbabura.shorturlgenerator.controllers;

import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;

@RestController
public class RedirectController {
    private final Url_matching_Service service;

    @Autowired
    public RedirectController(Url_matching_Service service) {
        this.service = service;
    }

    @GetMapping("/{shortUrlEnding}")
    public RedirectView redirect(HttpServletRequest url, @PathVariable String shortUrlEnding) {
        String result = service.findOriginalUrlByShortUrl(url.getRequestURL().toString());
        return new RedirectView(result);
    }
}
