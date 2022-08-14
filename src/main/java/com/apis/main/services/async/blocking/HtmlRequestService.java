package com.apis.main.services.async.blocking;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class HtmlRequestService implements Callable<String> {

    private RestTemplate restTemplate = new RestTemplate();
    private final String movieName;
    private final String SEARCH_URL = "https://www.watchseries1.video/search/";

    public HtmlRequestService(String movieName){
        this.movieName = movieName;
    }

    @Override
    public String call() throws Exception {
        return restTemplate.getForObject(SEARCH_URL+movieName, String.class);
    }
}
