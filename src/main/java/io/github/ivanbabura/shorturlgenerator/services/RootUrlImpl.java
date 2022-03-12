package io.github.ivanbabura.shorturlgenerator.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class RootUrlImpl implements RootUrl {
    private static String ROOT_URL;

    public RootUrlImpl() {
        try {
            ROOT_URL = readFromFileRootUrl();
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(RootUrlImpl.class);
            logger.error("Exception on work with Root_URL. ROOT_URL = " + ROOT_URL);
            logger.error("There is server must be stopped. But now i did this crutch");
            logger.error("ROOT_URL will be change to " + ROOT_URL);
            ROOT_URL = "http://localhost:8080/";
        }
    }

    public String getRootUrl() {
        return ROOT_URL;
    }

    private String readFromFileRootUrl() throws IOException {
        File file = new File("src/main/resources/rootUrl.properties");
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(file);
        properties.load(fileReader);
        fileReader.close();
        String result = properties.getProperty("rootURL");
        if (result == null)
            throw new IOException();
        return result;
    }
}
