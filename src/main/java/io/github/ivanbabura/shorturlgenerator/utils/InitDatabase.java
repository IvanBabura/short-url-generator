package io.github.ivanbabura.shorturlgenerator.utils;

import io.github.ivanbabura.shorturlgenerator.entities.ProgramOption;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.services.ProgramOptionService;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Service
public class InitDatabase implements ApplicationRunner {
    private final Url_matching_Service url_matching_service;
    private final ProgramOptionService programOptionService;

    @Autowired
    public InitDatabase(Url_matching_Service url_matching_service, ProgramOptionService programOptionService) {
        this.url_matching_service = url_matching_service;
        this.programOptionService = programOptionService;
    }

    @Override
    public void run(ApplicationArguments args) {
        initUrl_matchingDatabase();
        initProgram_optionDatabase();
    }

    public void initUrl_matchingDatabase() {
        long count = url_matching_service.count();
        if (count == 0) {
            String shortUrl;
            Url_matching urls_1 = new Url_matching();
            urls_1.setOriginalUrl("https://www.google.ru/");
            shortUrl = url_matching_service.generateShortUrl();
            urls_1.setShortUrl(shortUrl);
            url_matching_service.save(urls_1);

            Url_matching urls_2 = new Url_matching();
            urls_2.setOriginalUrl("http://yandex.ru/");
            shortUrl = url_matching_service.generateShortUrl();
            urls_2.setShortUrl(shortUrl);
            url_matching_service.save(urls_2);

            System.out.println("Success Url_matching database init.");
        } else {
            System.out.println("Url_matching Database already initialized.");
        }
    }

    public void initProgram_optionDatabase(){
        long count = programOptionService.count();
        if (count == 0){
            ProgramOption rootUrl = new ProgramOption();
            rootUrl.setNameOption("ROOT_URL");
            rootUrl.setValueOption("http://localhost:8080/");
            programOptionService.save(rootUrl);

            ProgramOption ttl = new ProgramOption();
            ttl.setNameOption("ttl");
            ttl.setValueOption("600");
            programOptionService.save(ttl);

            System.out.println("Success ProgramOption database init.");
        } else {
            System.out.println("ProgramOption Database already initialized.");
        }
    }
}
