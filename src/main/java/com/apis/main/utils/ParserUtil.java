package com.apis.main.utils;

import com.apis.main.entities.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserUtil {

    public static String getLinkFromHtml(String htmlDocument){
        try {
            Document document = Jsoup.parse(htmlDocument);
            Elements movieDivs = document.select("div.film_list-wrap");
            return movieDivs.first().firstElementChild().select(".film-poster a").attr("href");
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return "";
    }

    public static List<String> getAllRelevantLinksFromHtml(String htmlDocument){
        List<String> result = null;
        try{
            Document document = Jsoup.parse(htmlDocument);
            result = document.select("div.film_list-wrap").select("div.flw-item").select("div.film-detail").select("a").eachAttr("href");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return result;
    }


    public static Movie getMovieFromHtmlDocument(String htmlDocument){
        Movie movie = null;


        try{
            Document document = Jsoup.parse(htmlDocument);

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

    public static List<Movie> getAllMovies(String htmlDocument){
        List<Movie> result = new ArrayList<>();


        try{
            Document document = Jsoup.parse(htmlDocument);

            Iterator<Element> elementIterator = document.select("div.film_list-wrap").first().select("div.flw-item").iterator();

            while (elementIterator.hasNext()){
                Elements elements = elementIterator.next().select("div.film-poster");

                String imageSource = elements.select("img").attr("data-src");
                String url = elements.select("a").attr("href");
                String name = elements.select("a").attr("title");

                result.add(new Movie(name, url, imageSource));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


}
