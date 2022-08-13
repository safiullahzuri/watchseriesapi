package com.apis.main.controllers;

import com.apis.main.entities.Movie;
import com.apis.main.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/find")
    public ResponseEntity<Movie> findMovie(@RequestParam("movie") String movieName){
        Movie movie = movieService.getMovie(movieName);

        if (movie == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Movie>> findAllMovies(@RequestParam("movie") String movieName){
        List<Movie> movies = movieService.getAllMovies(movieName);

        if (movies == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }



}
