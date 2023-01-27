//package com.driver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MovieService {
//
//    @Autowired
//    MovieRepository movieRepository;
//    public String addMovie(Movie movie) {
//        return movieRepository.addMovie(movie);
//    }
//
//    public String addDirector(Director director) {
//        return movieRepository.addDirector(director);
//    }
//
//    public String addMovieDirectorPair(String movie, String director) {
//        return movieRepository.addMovieDirectorPair(movie,director);
//    }
//
//    public Movie getMovieByName(String movieName) {
//        return movieRepository.getMovieByName(movieName);
//    }
//
//    public Director getDirectorByName(String directorName) {
//        return movieRepository.getDirectorByName(directorName);
//    }
//
//    public List getMoviesByDirectorName(String directorName) {
//        return movieRepository.getMoviesByDirectorName(directorName);
//    }
//
//    public List findAllMovies() {
//        return movieRepository.findAllMovies();
//    }
//
//    public boolean deleteDirectorByName(String directorName) {
//        return movieRepository.deleteDirectorByName(directorName);
//    }
//
//    public boolean deleteAllDirectors() {
//        return movieRepository.deleteAllDirectors();
//    }
//}

package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        movieRepository.addMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String directorName) {
        movieRepository.deleteDirectorByName(directorName);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}