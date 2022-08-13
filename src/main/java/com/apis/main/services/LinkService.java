package com.apis.main.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class LinkService {

    private final RestTemplate restTemplate;
    private final String SEARCH_URL = "https://www.watchseries1.video/search/";


    public LinkService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public String getLink(String movie){
        String htmlResult = restTemplate.getForObject(SEARCH_URL+movie, String.class);

        try {
            Document document = Jsoup.parse(htmlResult);
            Elements movieDivs = document.select("div.film_list-wrap");
            return movieDivs.first().firstElementChild().select(".film-poster a").attr("href");
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "";
    }

    public List<String> getAllRelevantLinks(String movie){
        String htmlResult = restTemplate.getForObject(SEARCH_URL+movie, String.class);

        List<String> result = null;
        try{
            Document document = Jsoup.parse(htmlResult);
            result = document.select("div.film_list-wrap").select("div.flw-item").select("div.film-detail").select("a").eachAttr("href");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return result;
    }

}
