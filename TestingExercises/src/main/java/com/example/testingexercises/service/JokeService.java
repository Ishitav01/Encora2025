package com.example.testingexercises.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class JokeService {

	private final RestTemplate restTemplate;
	private static final String JOKE_API_URL = "https://api.chucknorris.io/jokes/random";

	public JokeService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getJoke() {
		String response = restTemplate.getForObject(JOKE_API_URL, String.class);
		return response;
	}

	public String getJokeValue() {
		String response = restTemplate.getForObject(JOKE_API_URL, String.class);
	
		return response;
	}

	public String getJokeFromUrl(String url) {
		return restTemplate.getForObject(url, String.class);
	}
}