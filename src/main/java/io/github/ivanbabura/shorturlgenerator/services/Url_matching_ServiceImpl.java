package io.github.ivanbabura.shorturlgenerator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.repositories.Url_matching_Repository;
import java.util.List;

@Service
public class Url_matching_ServiceImpl implements Url_matching_Service{
    private final Url_matching_Repository repository;
    private final ShortUrlGenerator generator;
    
    @Autowired
    public Url_matching_ServiceImpl(Url_matching_Repository repository, ShortUrlGenerator generator) {
        this.repository = repository;
        this.generator = generator;
    }

    @Override
    public void save(Url_matching url_matching) {
        repository.save(url_matching);
    }

    @Override
    public Url_matching findByShortUrl(String shortUrl) {
        return repository.findByShortUrl(shortUrl);
    }

    @Override
    public Url_matching findByOriginalUrl(String originalUrl) {
        return repository.findByOriginalUrl(originalUrl);
    }

    @Override
    public List<Url_matching> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Url_matching url_matching) {
        repository.delete(url_matching);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public String generateShortUrl() {
        String shortUrl;
        int attempts = 5;//count of attempts to generate
        while ((attempts--)>0) {
            shortUrl = generator.generateShortUrl();
            if (findByShortUrl(shortUrl) == null) {
                return shortUrl;
            }
        }
        //I didn't figure out how to do "message of Error" better.
        //It doesn't matter now.
        //for example: This method can be rewritten like ResponseEntity
        //or throw special exception
        return "ERROR";
    }

    @Override
    public String findOriginalUrlByShortUrl(String shortUrl) {
        Url_matching result = repository.findByShortUrl(shortUrl);
        return (result!=null)?
                result.getOriginalUrl():
                null;
    }
}
