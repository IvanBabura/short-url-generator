package io.github.ivanbabura.shorturlgenerator.services;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class RootUrlImpl implements RootUrl {
    private static String ROOT_URL;

    public RootUrlImpl() {
        ROOT_URL = readFromFileRootUrl();
    }

    public String getRootUrl(){
        return ROOT_URL;
    }
    
    private String readFromFileRootUrl(){
        File file = new File("src/main/resources/rootUrl.properties");
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader(file)){
            properties.load(fileReader);
        } catch (IOException e) {
            //TODO: handle the exception
            e.printStackTrace();
        }
        return properties.getProperty("rootURL");
    }
}
