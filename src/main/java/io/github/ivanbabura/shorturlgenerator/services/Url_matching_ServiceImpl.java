package io.github.ivanbabura.shorturlgenerator.services;

import io.github.ivanbabura.shorturlgenerator.exceptions.*;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.repositories.Url_matching_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class Url_matching_ServiceImpl implements Url_matching_Service {
    private final Url_matching_Repository repository;
    private final ShortUrlGenerator generator;

    @Autowired
    public Url_matching_ServiceImpl(Url_matching_Repository repository, ShortUrlGenerator generator) {
        this.repository = repository;
        this.generator = generator;
    }

    @Override
    public long count() {
        return repository.count();
    }

    public Url_matching create(String originalUrl) {
        if (originalUrl.equals(""))
            throw new BadRequestException("Object is null.");
        try {
            URL url = new URL(originalUrl);
        } catch (MalformedURLException e) {
            throw new IncorrectUrlException("Error into URL: " + originalUrl);
        }
        Url_matching result = repository.findByOriginalUrl(originalUrl);
        if (result != null)
            throw new FoundException(result);
        result = new Url_matching();
        result.setOriginalUrl(originalUrl);
        result.setShortUrl(generateShortUrl());
        return result;
    }

    @Override
    public void save(Url_matching url_matching) {
        if (url_matching == null)
            throw new BadRequestException("Object is null.");
        if (url_matching.getOriginalUrl().equals("") ||
                url_matching.getOriginalUrl() == null)
            throw new BadRequestException("OriginalUrl can't be empty.");
        if (url_matching.getShortUrl().equals("") ||
                url_matching.getShortUrl() == null)
            throw new BadRequestException("ShortUrl can't be empty.");
        repository.save(url_matching);
    }

    @Override
    public void delete(Url_matching url_matching) {
        if (url_matching == null)
            throw new BadRequestException("Object is null.");
        repository.delete(url_matching);
    }

    @Override
    public Url_matching findByShortUrl(String shortUrl) {
        Url_matching result = repository.findByShortUrl(shortUrl);
        if (result == null)
            throw new NotFoundException("Not found shortUrl: " + shortUrl + ".");
        return result;
    }

    @Override
    public Url_matching findByOriginalUrl(String originalUrl) {
        Url_matching result = repository.findByOriginalUrl(originalUrl);
        if (result == null)
            throw new NotFoundException("Not found originalUrl: " + originalUrl + ".");
        return result;
    }

    @Override
    public String findOriginalUrlByShortUrl(String shortUrl) {
        if (shortUrl == null)
            throw new BadRequestException("shortUrl can't be empty.");
        Url_matching result = repository.findByShortUrl(shortUrl);
        if (result == null)
            throw new NotFoundException("Not found shortUrl: " + shortUrl + ".");
        return result.getOriginalUrl();
    }

    @Override
    public List<Url_matching> findAll() {
        List<Url_matching> url_matching_List = repository.findAll();
        if (url_matching_List.isEmpty()) {
            throw new NoContentException("I have not any entry.");
        }
        return url_matching_List;
    }

    @Override
    public String generateShortUrl() {
        String shortUrl;
        int attempts = 5;//count of attempts to generate
        while ((attempts--) > 0) {
            shortUrl = generator.generateShortUrl();
            if (repository.findByShortUrl(shortUrl) == null) {
                return shortUrl;
            }
        }
        throw new GenerateShortUrlException("Generation error. Number of attempts: " +
                attempts + ". Please try again.");
    }
}