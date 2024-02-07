package com.example.giphy.services;

import com.example.giphy.model.Gif;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
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
    @Value("${giphy.api.url}")
    private String apiUrl;
    @Value("${giphy.api.key}")
    private String key;

    public List<Gif> getSearchResults(String searchString) {

        String url = this.apiUrl + this.key + "&limit=10&q=" + searchString;


        HttpEntity<String> httpEntity = new HttpEntity<>(""); //this is where you put the body and the header in the request
        RestTemplate restTemplate = new RestTemplate(); //this is the uber or taxi - responsible for sending request to server

        // These two are new! We'll see how they work.
        ObjectMapper objectMapper = new ObjectMapper(); //allows us to transform a string that reps a json into an object of type json node
        JsonNode jsonNode; //java's representation of a valid fragment of json node - We need a custom class for this
        List<Gif> gifList = new ArrayList<>(); //you need a place to store them
        // Code start:
        //call the api's endpoint
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class); //make the call with a rest template - tell exchange where your sending the request & how am I sending this request
        // we want to lastly include the entity - then everything will be returned as a string
        System.out.println(response.getBody()); //same content as I saw in postman

        //if that giant string is a valid json - no errors you can transform it into an object of json node
        try{
            jsonNode = objectMapper.readTree(response.getBody()); //not all strings are valid json nodes - they will show an error (ie readtree) wrap in try catch
            //now we can use methods to transverse the node (or the section called data)
            JsonNode root = jsonNode.path("data");  //im sitting outside the root of the response.  With I path data, i move into it.
            //the issue is that data is now an array

            for (int i=0; i < root.size(); i++){
                String title = root.path(i).path("title").asText(); //asText is like to string but will remove the double quotes
                String username = root.path(i).path("username").asText();
                String gifUrl = root.path(i).path("images").path("original").path("url").asText(); // if you have to go several branches in your json data

                //each time you iterate, you're pulling these three pieces of information.

                Gif gif = new Gif(gifUrl, username, title); //based on what the constructor needs  //store in a list and return to controller
                //each time you iterate , you make an object
                gifList.add(gif);
            }


             } catch(JsonProcessingException e){

        }

        return gifList;
    }

}
