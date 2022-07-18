package io.github.ivanbabura.shorturlgenerator.controllers;

import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.repositories.Url_matching_Repository;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_ServiceImpl;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Url_matching_Repository repository;

    @Autowired
    Url_matching_ServiceImpl service;

    ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void get_all() {
        when(repository.findAll()).thenReturn(List.of(
                new Url_matching("blablabla", "la"),
                new Url_matching("original", "short"),
                new Url_matching("skrskr", "brr")));
        try {
            MvcResult mockResult = mockMvc.perform(MockMvcRequestBuilders.get("/all_urls"))
                    .andExpect(status().isOk())
                    .andReturn();
            var result = om.readValue(mockResult.getResponse().getContentAsString(),List.class);
            assertEquals(3, result.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getByOriginalUrl() {
        String originalUrl = "originalTest";
        List<Url_matching> list = List.of(
                new Url_matching("blablabla", "la"),
                new Url_matching(originalUrl, "shortTest"),
                new Url_matching("skrskr", "brr"));
        try {
            when(repository.findByOriginalUrl(originalUrl)).thenReturn(list.stream()
                    .filter(x -> x.getOriginalUrl().equals(originalUrl)).findFirst().orElse(new Url_matching()));
            mockMvc.perform(MockMvcRequestBuilders.get("/url")
                    .param("originalUrl", originalUrl))
                    .andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getByShortUrl() {
        String shortUrl = "shortTest";
        List<Url_matching> list = List.of(
                new Url_matching("blablabla", "la"),
                new Url_matching("originalTest", shortUrl),
                new Url_matching("skrskr", "brr"));
        try {
            when(repository.findByShortUrl(shortUrl)).thenReturn(list.stream()
                    .filter(x -> x.getShortUrl().equals(shortUrl)).findFirst().orElse(new Url_matching()));
            mockMvc.perform(MockMvcRequestBuilders.get("/url")
                    .param("shortUrl", shortUrl))
                    .andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void create() {
        String jsonString = getJsonString(new Url_matching("https://ya.ru/23"));
        try {
            MvcResult mockResult = mockMvc.perform(
                    post("/url").content(jsonString)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            final int resultStatus = mockResult.getResponse().getStatus();
            assertTrue(resultStatus == 302 || resultStatus == 201);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test()
    void delete() {
        Url_matching url_matching = new Url_matching("https://ya.ru/23");
        String jsonString = getJsonString(url_matching);
        try {
            when(repository.findByOriginalUrl(url_matching.getOriginalUrl())).thenReturn(url_matching);
            mockMvc.perform(MockMvcRequestBuilders.delete("/url")
                    .content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
            verify(repository, times(1)).delete(url_matching);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    private String getJsonString(Url_matching url_matching) {
        String jsonString = null;
        try {
            jsonString = om.writeValueAsString(url_matching);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
            Assumptions.assumeFalse(true, "tested JSON convert fail.");
        }
        return jsonString;
    }
}