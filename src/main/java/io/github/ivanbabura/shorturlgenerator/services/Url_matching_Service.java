package io.github.ivanbabura.shorturlgenerator.services;

import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import java.util.List;

public interface Url_matching_Service{
    void save(Url_matching url_matching);
    Url_matching findByShortUrl(String shortUrl);
    Url_matching findByOriginalUrl(String originalUrl);
    List<Url_matching> findAll();
    void delete(Url_matching url_matching);
    long count();
    String generateShortUrl();
    String findOriginalUrlByShortUrl(String shortUrl);
}
