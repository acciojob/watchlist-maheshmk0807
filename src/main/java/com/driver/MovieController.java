//package com.driver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.websocket.server.PathParam;
//import java.util.List;
//
//@RestController
//public class MovieController {
//
//
//    @Autowired
//    MovieService movieService;
//
//    @PostMapping("/movies/add-movie")
//    public ResponseEntity addMovie(@RequestBody Movie movie){ //-----OK-----
//        String resp = movieService.addMovie(movie);
//        return new ResponseEntity<>(resp, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/movies/add-director")
//    public ResponseEntity addDirector(@RequestBody Director director){ //-----OK-----
//        String resp = movieService.addDirector(director);
//        return new ResponseEntity<>(resp, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/movies/add-movie-director-pair")
//    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){ //-----OK-----
//        String resp = movieService.addMovieDirectorPair(movie,director);
//        if(resp.equals("Not_Found"))
//            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>(resp, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/movies/get-movie-by-name/{name}")
//    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
//        Movie resp = movieService.getMovieByName(movieName);
//        if(resp==null)
//            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity(resp, HttpStatus.FOUND);
//    }
//
//    @GetMapping("/movies/get-director-by-name/{name}")
//    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
//        Director resp = movieService.getDirectorByName(directorName);
//        if(resp.equals("Not_Found"))
//            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity(resp, HttpStatus.FOUND);
//    }
//
//    @GetMapping("/movies/get-movies-by-director-name/{director}")
//    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
//        List resp = movieService.getMoviesByDirectorName(directorName);
//        if(resp.size()==0)
//            return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity(resp, HttpStatus.FOUND);
//    }
//
//    @GetMapping("/movies/get-all-movies")
//    public ResponseEntity findAllMovies(){
//        List resp = movieService.findAllMovies();
//        return new ResponseEntity<>(resp,HttpStatus.FOUND);
//    }
//
//    @DeleteMapping("/movies/delete-director-by-name")
//    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
//        if(movieService.deleteDirectorByName(directorName))
//            return new ResponseEntity<>("Deleted !",HttpStatus.OK);
//        else
//            return new ResponseEntity<>("Failed !",HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/movies/delete-all-directors")
//    public ResponseEntity deleteAllDirectors(){
//        if(movieService.deleteAllDirectors())
//            return new ResponseEntity<>("Deleted !",HttpStatus.OK);
//        else
//            return new ResponseEntity<>("Failed !",HttpStatus.NOT_FOUND);
//    }
//}
//
//
//

package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>("success", HttpStatus.FOUND);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity<>("success", HttpStatus.FOUND);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName) {
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>("success", HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName) {
        Movie response = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName) {
        Director response = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName) {
        List<String> response = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> response = movieService.findAllMovies();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName) {
        movieService.deleteDirectorByName((directorName));
        return new ResponseEntity<>("success", HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("success", HttpStatus.FOUND);
    }
}
