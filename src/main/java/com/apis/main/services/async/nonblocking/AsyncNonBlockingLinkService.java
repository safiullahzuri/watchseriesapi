package com.apis.main.services.async.nonblocking;

import com.apis.main.services.async.blocking.HtmlRequestService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Service
public class AsyncNonBlockingLinkService {
//
//    private final NonBlockingHtmlRequestService nonBlockingHtmlRequestService;
//
//    public AsyncNonBlockingLinkService(){
//        this.nonBlockingHtmlRequestService = new NonBlockingHtmlRequestService();
//    }
//
//    public String getLink(String movieName){
//
//        Mono<String> htmlResultMono = nonBlockingHtmlRequestService.getHtmlResult(movieName);
//
//        try {
//            htmlResultMono
//                    .subscribe(mono -> Jsoup.parse(mono))
//
//
//            htmlResultMono.zipWith(Jsoup.parse(htmlResultMono.))
//
//            Document document = Jsoup.parse(htmlResultMono.);
//            Elements movieDivs = document.select("div.film_list-wrap");
//            return movieDivs.first().firstElementChild().select(".film-poster a").attr("href");
//        }catch (NullPointerException | ExecutionException | InterruptedException e){
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public List<String> getAllRelevantLinks(String movieName){
//
//        Future<String> htmlResultFuture = executors.submit(new HtmlRequestService(movieName));
//
//        List<String> result = null;
//        try{
//            Document document = Jsoup.parse(htmlResultFuture.get());
//            result = document.select("div.film_list-wrap").select("div.flw-item").select("div.film-detail").select("a").eachAttr("href");
//
//        }catch (NullPointerException | InterruptedException | ExecutionException e){
//            e.printStackTrace();
//        }
//        return result;
//    }
}
