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
            url_matching_service.save(new Url_matching("https://www.google.ru/", url_matching_service.generateShortUrl()));
            url_matching_service.save(new Url_matching("http://yandex.ru/", url_matching_service.generateShortUrl()));
            System.out.println("Success Url_matching database init.");
        } else {
            System.out.println("Url_matching Database already initialized.");
        }
    }

    public void initProgram_optionDatabase(){
        long count = programOptionService.count();
        if (count == 0){
            programOptionService.save(new ProgramOption("ROOT_URL","http://localhost:8080/"));
            programOptionService.save(new ProgramOption("ttl","600"));
            System.out.println("Success ProgramOption database init.");
        } else {
            System.out.println("ProgramOption Database already initialized.");
        }
    }
}
