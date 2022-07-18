package io.github.ivanbabura.shorturlgenerator.services;

import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import java.util.List;

public interface Url_matching_Service{
    long count();
    Url_matching createWithoutSave(String originalUrl);
    void save(Url_matching url_matching);
    void delete(Url_matching url_matching);
    Url_matching findByShortUrl(String shortUrl);
    Url_matching checkTtlOnFindByShortUrl(String shortUrl);
    Url_matching findByOriginalUrl(String originalUrl);
    Url_matching checkTtlOnFindByOriginalUrl(String originalUrl);
    String findOriginalUrlByShortUrl(String shortUrl);
    List<Url_matching> findAll();
    String generateShortUrl();
}
