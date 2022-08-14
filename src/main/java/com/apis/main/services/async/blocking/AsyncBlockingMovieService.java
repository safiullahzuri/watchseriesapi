package com.apis.main.services.async.blocking;

import com.apis.main.entities.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class AsyncBlockingMovieService {

    ExecutorService executors = Executors.newFixedThreadPool(10);


    public Movie getMovie(String movieName){
        Movie movie = null;

        Future<String> htmlResultFuture = executors.submit(new HtmlRequestService(movieName));

        try{
            Document document = Jsoup.parse(htmlResultFuture.get());

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

    public List<Movie> getAllMovies(String movieName){
        List<Movie> result = new ArrayList<>();

        Future<String> htmlResultFuture = executors.submit(new HtmlRequestService(movieName));

        try{
            Document document = Jsoup.parse(htmlResultFuture.get());

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
