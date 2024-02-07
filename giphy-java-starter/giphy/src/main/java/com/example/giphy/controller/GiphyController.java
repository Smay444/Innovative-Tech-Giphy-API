package com.example.giphy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.giphy.model.Gif;
import com.example.giphy.services.GiphyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GiphyController {

	@Autowired
	GiphyService giphyService;


	@RequestMapping(path="/test", method=RequestMethod.GET)
	public List<Gif> test(@RequestParam String query) {
		

		return giphyService.getSearchResults(query);
		
	}
	
}

//vue angular react - client side
//server - springboot
//the server might need information that it just doesn't have - it's possible for a server to reach out to another server
//the client might need to reach out to two different servers now
//this could go on to look more like a web
//microservice - a server that provides a well-defined functionality or tasks. (API)
//google microservices architecture
//need to be able to parse their responses in an effective manner
//today I learned about jackson
//giphy - service that provides animated gifs - started as a repositiory of animated gifs
//the owners have built an api around it in order for us to incorporate it into our application
//with API's
	//step one how do i authenticate
	//step two read documentation
		// the more you know about it up front the easier it will be for you to write code later on
//i want to enter a search term - selct a gif
//take all of this information and try it out in postman
// in postman - https://api.giphy.com/v1/gifs/search?api_key=UZ6s5nfxK2FwIw1toENimYb97mijgbkw&q=doggo&limit=10
//jackson will help us extract only the json properties that you want.
