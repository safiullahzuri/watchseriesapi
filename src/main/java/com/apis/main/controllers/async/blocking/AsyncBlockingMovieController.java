package com.apis.main.controllers.async.blocking;


import com.apis.main.entities.Movie;
import com.apis.main.services.MovieService;
import com.apis.main.services.async.blocking.AsyncBlockingMovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/async/blocking/movie")
public class AsyncBlockingMovieController {

    private final AsyncBlockingMovieService blockingMovieService;


    public AsyncBlockingMovieController(AsyncBlockingMovieService movieService) {
        this.blockingMovieService = movieService;
    }

    @GetMapping("/find")
    public ResponseEntity<Movie> findMovie(@RequestParam("movie") String movieName){
        Movie movie = blockingMovieService.getMovie(movieName);

        if (movie == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Movie>> findAllMovies(@RequestParam("movie") String movieName){
        List<Movie> movies = blockingMovieService.getAllMovies(movieName);

        if (movies == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


}
