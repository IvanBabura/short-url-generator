package io.github.ivanbabura.shorturlgenerator.services;

import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.exceptions.NotFoundException;
import io.github.ivanbabura.shorturlgenerator.repositories.Url_matching_Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class Url_matching_ServiceImplTest {
    @MockBean
    private Url_matching_Repository repository;

    @Autowired
    private Url_matching_Service service;

    @Autowired
    private ShortUrlGenerator generator;

    @Autowired
    private TtlControlService ttlControlService;

    @Test
    void findByShortUrl() {
        final String originalUrl = "originalUrl";
        final String shortUrl = "shortUrl";
        Url_matching testedUrl = new Url_matching(originalUrl, shortUrl);
        when(repository.findByShortUrl(shortUrl))
                .thenReturn(Stream.of(
                        new Url_matching("originalUrlTest1", "shortUrlTest1"),
                        testedUrl,
                        new Url_matching("originalUrlTest3", "shortUrlTest3"))
                        .filter(x -> x.getShortUrl().equals(shortUrl))
                        .findFirst().orElse(new Url_matching()));
        assertEquals(testedUrl, service.findByShortUrl(shortUrl));
    }

    @Test
    void findByOriginalUrl() {
        final String originalUrl = "originalUrl";
        final String shortUrl = "shortUrl";
        Url_matching testedUrl = new Url_matching(originalUrl, shortUrl);
        when(repository.findByOriginalUrl(originalUrl)).thenReturn(
                Stream.of(new Url_matching("originalUrlTest1", "shortUrlTest1"),
                        testedUrl,
                        new Url_matching("originalUrlTest3", "shortUrlTest3"))
                        .filter(x -> x.getOriginalUrl().equals(originalUrl))
                        .findFirst().orElse(new Url_matching()));
        assertEquals(testedUrl, service.findByOriginalUrl(originalUrl));
    }

    @Test
    void findOriginalUrlByShortUrl() {
        final String originalUrl = "originalUrl";
        final String shortUrl = "shortUrl";
        Url_matching testedUrl = new Url_matching(originalUrl, shortUrl);
        when(repository.findByShortUrl(shortUrl)).thenReturn(
                Stream.of(new Url_matching("originalUrlTest1", "shortUrlTest1"),
                        testedUrl,
                        new Url_matching("originalUrlTest3", "shortUrlTest3"))
                        .filter(x -> x.getShortUrl().equals(shortUrl))
                        .findFirst().orElse(new Url_matching()));
        assertEquals(originalUrl, service.findByShortUrl(shortUrl).getOriginalUrl());
    }

    @Test
    void findAll() {
        replaceFindAllMethodInRepository();
        assertEquals(3, service.findAll().size());
    }

    @Test
    void generateShortUrl() {
        String result = generator.generateShortUrl();
        assert (!result.isEmpty());
    }

    @Test
    void checkOnTtlAndDeleteIfEnded() {
        replaceFindAllMethodInRepository();
        List<Url_matching> list = service.findAll();
        final int index = 1;
        Url_matching el = list.get(index);
        if (Duration.between(el.getDateTime(), Instant.now()).toSeconds() > ttlControlService.getTtl_in_seconds()) {
            service.delete(el);
            try {
                service.findByOriginalUrl(el.getOriginalUrl());
            } catch (NotFoundException e) {
                assertEquals(NotFoundException.class.getSimpleName(), e.getClass().getSimpleName());
            }
        } else {
            assertEquals(el, service.findAll().get(index));
        }
    }

    private void replaceFindAllMethodInRepository(){
        when(repository.findAll()).thenReturn(
                Stream.of(new Url_matching("originalUrlTest1", "shortUrlTest1"),
                        new Url_matching("originalUrlTest2", "shortUrlTest1"),
                        new Url_matching("originalUrlTest3", "shortUrlTest1"))
                        .collect(Collectors.toList()));
    }
}