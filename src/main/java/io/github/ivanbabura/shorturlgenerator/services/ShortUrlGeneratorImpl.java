package io.github.ivanbabura.shorturlgenerator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class ShortUrlGeneratorImpl implements ShortUrlGenerator {
    private static String ROOT_URL;

    @Autowired
    public ShortUrlGeneratorImpl(RootUrl rootUrl) {
        ROOT_URL = rootUrl.getRootUrl();
    }

    public String generateShortUrl() {
        //TODO: This simple algorithm have limit only 62^5 variations
        String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int lengthOfShortUrl = 5;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(ROOT_URL);
        while (lengthOfShortUrl-- > 0) {
            int number = random.nextInt(symbols.length());
            sb.append(symbols.charAt(number));
        }
        return sb.toString();
    }
}
