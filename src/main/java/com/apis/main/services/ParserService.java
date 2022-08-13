package com.apis.main.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParserService {

    public String getLink(String htmlResult){
        try {
            Document document = Jsoup.parse(htmlResult);
            Elements parentDiv = document.select("div.film_list-wrap");
            return parentDiv.first().firstElementChild().select(".film-poster a").attr("href");
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "";
    }

    public List<String> getAllRelevantLinks(String htmlResult){
        List<String> result = null;

        try{
            Document document = Jsoup.parse(htmlResult);
            Elements parentDiv = document.select("div.film_list-wrap");



        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return result;
    }

}
