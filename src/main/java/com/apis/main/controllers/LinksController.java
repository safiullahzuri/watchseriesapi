package com.apis.main.controllers;

import com.apis.main.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/link")
public class LinksController {

    private final LinkService linkService;

    public LinksController(LinkService linkService) {
        this.linkService = linkService;
    }


    @GetMapping("/find")
    public ResponseEntity<String> findMovieLink(@RequestParam("movie") String movieName){

        String link = linkService.getLink(movieName);

        if (link.isEmpty()){
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(link, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<String>> findMovieLinks(@RequestParam("movie") String movieName){
        List<String> links = linkService.getAllRelevantLinks(movieName);

        if (links == null || links.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(links, HttpStatus.OK);
    }



}
