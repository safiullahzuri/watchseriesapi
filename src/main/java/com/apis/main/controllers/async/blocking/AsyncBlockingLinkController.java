package com.apis.main.controllers.async.blocking;

import com.apis.main.services.async.blocking.AsyncBlockingLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/async/blocking/link")
public class AsyncBlockingLinkController {

    private final AsyncBlockingLinkService blockingLinkService;

    public AsyncBlockingLinkController(AsyncBlockingLinkService linkService) {
        this.blockingLinkService = linkService;
    }


    @GetMapping("/find")
    public ResponseEntity<String> findMovieLink(@RequestParam("movie") String movieName){

        String link = blockingLinkService.getLink(movieName);

        if (link.isEmpty()){
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(link, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<String>> findMovieLinks(@RequestParam("movie") String movieName){
        List<String> links = blockingLinkService.getAllRelevantLinks(movieName);

        if (links == null || links.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(links, HttpStatus.OK);
    }

}
