package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MovieController {


    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String resp = movieService.addMovie(movie);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String resp = movieService.addDirector(director);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){
        String resp = movieService.addMovieDirectorPair(movie,director);
        if(resp.equals("Not_Found"))
            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathParam("name") String movieName){
        String resp = movieService.getMovieByName(movieName);
        if(resp.equals("Not_Found"))
            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(resp, HttpStatus.OK);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathParam("name") String directorName){
        String resp = movieService.getDirectorByName(directorName);
        if(resp.equals("Not_Found"))
            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(resp, HttpStatus.OK);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathParam("name") String directorName){
        List resp = movieService.getMoviesByDirectorName(directorName);
        if(resp.size()==0)
            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(resp, HttpStatus.OK);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List resp = movieService.findAllMovies();
        if(resp.size()==0)
            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(resp, HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        if(movieService.deleteDirectorByName(directorName))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        if(movieService.deleteAllDirectors())
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
























