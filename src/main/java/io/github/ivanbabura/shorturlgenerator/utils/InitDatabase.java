package io.github.ivanbabura.shorturlgenerator.utils;

import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Service
public class InitDatabase implements ApplicationRunner {
    private final Url_matching_Service service;

    @Autowired
    public InitDatabase(Url_matching_Service service) {
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args){
        long count = service.count();
        if (count == 0){
            String shortUrl;
            Url_matching urls_1 = new Url_matching();
            urls_1.setOriginalUrl("https://www.google.ru/");
            shortUrl = service.generateShortUrl();
            urls_1.setShortUrl(shortUrl);
            service.save(urls_1);

            Url_matching urls_2 = new Url_matching();
            urls_2.setOriginalUrl("http://yandex.ru/");
            shortUrl = service.generateShortUrl();
            urls_2.setShortUrl(shortUrl);
            service.save(urls_2);

            System.out.println("Success database init.");
        } else {
            System.out.println("Database already initialized.");
        }
    }
}
