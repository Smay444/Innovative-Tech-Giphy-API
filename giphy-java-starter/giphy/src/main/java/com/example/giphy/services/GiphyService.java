package com.example.giphy.services;

import com.example.giphy.model.Gif;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class GiphyService {

    private String apiURL;
    private String key;

    public List<Gif> getSearchResults(String searchString) {

        String url = this.apiURL + this.key + "&limit=10&q=" + searchString;


        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        // These two are new! We'll see how they work.
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        // Code start:



        return null;
    }
    //My Key = UZ6s5nfxK2FwIw1toENimYb97mijgbkw
}
