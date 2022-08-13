package com.apis.main.services;


import com.apis.main.entities.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {

    private final RestTemplate restTemplate;
    private final String SEARCH_URL = "https://www.watchseries1.video/search/";


    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovie(String movieName){
        Movie movie = null;

        String htmlResult = restTemplate.getForObject(SEARCH_URL+movieName, String.class);

        try{
            Document document = Jsoup.parse(htmlResult);

            Elements elements = document.select("div.film_list-wrap").first().firstElementChild().select("div.film-poster");
            String imageSource = elements.select("img").attr("data-src");
            String url = elements.select("a").attr("href");
            String name = elements.select("a").attr("title");

            movie = new Movie(name, url, imageSource);
        }catch (Exception e){
            e.printStackTrace();
        }

        return movie;
    }

    public List<Movie> getAllMovies(String movie){
        return null;
    }


}
