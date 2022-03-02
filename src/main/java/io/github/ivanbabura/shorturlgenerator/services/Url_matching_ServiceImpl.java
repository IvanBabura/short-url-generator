package io.github.ivanbabura.shorturlgenerator.services;

import io.github.ivanbabura.shorturlgenerator.controllers.MainRestController;
import io.github.ivanbabura.shorturlgenerator.exceptions.BadRequestException;
import io.github.ivanbabura.shorturlgenerator.exceptions.FoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.repositories.Url_matching_Repository;
import java.util.List;

@Service
public class Url_matching_ServiceImpl implements Url_matching_Service{
    private final Url_matching_Repository repository;
    private final ShortUrlGenerator generator;
    //TODO Embed logger
    private static final Logger logger = LoggerFactory.getLogger(MainRestController.class);

    @Override
    public long count() {
        return repository.count();
    }

    public Url_matching create(String originalUrl){
        //TODO: there might be a Check here: is it a url or rubbish?
        if (originalUrl.equals(""))
            throw new BadRequestException("object is null");
        Url_matching result = repository.findByOriginalUrl(originalUrl);
        if (result != null)
            throw new FoundException(result);
        result = new Url_matching();
        result.setOriginalUrl(originalUrl);
        result.setShortUrl(generateShortUrl());
        return result;
    }

    @Override
    public void save(Url_matching url_matching){
        if (url_matching == null)
            throw new BadRequestException("Object is null");
        if (url_matching.getOriginalUrl().equals("") ||
                url_matching.getOriginalUrl() == null)
            throw new BadRequestException("OriginalUrl can't be empty");
        if (url_matching.getShortUrl().equals("") ||
                        url_matching.getShortUrl() == null)
            throw new BadRequestException("ShortUrl can't be empty");
        repository.save(url_matching);
    }

    @Override
    public void delete(Url_matching url_matching) {
        if (url_matching == null)
            throw new BadRequestException("Object is null");
        repository.delete(url_matching);
    }

    @Autowired
    public Url_matching_ServiceImpl(Url_matching_Repository repository, ShortUrlGenerator generator) {
        this.repository = repository;
        this.generator = generator;
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
    public String findOriginalUrlByShortUrl(String shortUrl) {
        if(shortUrl==null) {
            throw new BadRequestException("shortUrl can't be empty");
        }
        Url_matching result = repository.findByShortUrl(shortUrl);
        if(result==null){
            throw new BadRequestException("I didn't found it!");
        }
        return result.getOriginalUrl();
    }
    @Override
    public List<Url_matching> findAll() {
        return repository.findAll();
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

}
