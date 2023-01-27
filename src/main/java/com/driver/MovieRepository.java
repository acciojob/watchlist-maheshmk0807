//package com.driver;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Repository
//public class MovieRepository {
//
//
//    private HashMap<String, Movie> movieMap;
//    private HashMap<String, Director> directorMap;
//    private HashMap<String, List<String>> directorMovieMapping;
//
//    public MovieRepository() {
//        this.movieMap = new HashMap<String, Movie>();
//        this.directorMap = new HashMap<String, Director>();
//        this.directorMovieMapping = new HashMap<String, List<String>>();
//    }
//
//    public String addMovie(Movie movie) {
//        movieMap.put(movie.getName(),movie);
//        return "Movie added successfully!";
//
//    }
//
//    public String addDirector(Director director) {
//        directorMap.put(director.getName(),director);
//        return "Director added successfully!";
//    }
//
//    public String addMovieDirectorPair(String movie, String director) {
//        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
//
//           // movieMap.put(movie, movieMap.get(movie));
//           // directorMap.put(director, directorMap.get(director));
//
//            List<String> currentMovies;
//            if(directorMovieMapping.containsKey(director))
//                currentMovies = directorMovieMapping.get(director);
//            else
//                currentMovies = new ArrayList<String>();
//
//            currentMovies.add(movie);
//            directorMovieMapping.put(director, currentMovies);
//
//            return "Pair added successfully !";
//        }
//        else
//            return "Not_Found";
//
//    }
//
//    public Movie getMovieByName(String movieName) {
//        if(movieMap.containsKey(movieName)){
//            return movieMap.get(movieName);
//        }
//        else{
//            return null;
//        }
//    }
//
//    public Director getDirectorByName(String directorName) {
//        return directorMap.getOrDefault(directorName, null);
//    }
//
//    public List getMoviesByDirectorName(String directorName) {
//        for(String s : directorMovieMapping.keySet()){
//            if(s.equals(directorName))
//                return directorMovieMapping.get(s);
//        }
//        return new ArrayList<>();
//    }
//
//    public List findAllMovies() {
//        List movies = new ArrayList<>();
//        for(String s : movieMap.keySet())
//            movies.add(s);
//        return movies;
//    }
//
//    public boolean deleteDirectorByName(String directorName) {
//        if(directorMap.containsKey(directorName)){
//            directorMap.remove(directorName);
//            return true;
//        }
//        else
//            return false;
//    }
//
//    public boolean deleteAllDirectors() {
//        directorMap.clear();
//        return true;
//    }
//}


package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {


    Map<String, Movie> dbMovie = new HashMap<>();
    Map<String, Director> dbDirector = new HashMap<>();
    HashMap<String, List<String>> movieDirectorDb = new HashMap<>();


    public void addMovie(Movie movie) {
        String name = movie.getName();
        dbMovie.put(name, movie);
    }

    public void addDirector(Director director) {
        String name = director.getName();
        dbDirector.put(name, director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(dbMovie.containsKey(movieName) && dbDirector.containsKey(directorName)){
            List<String> currentMovies = new ArrayList<>();
            if(movieDirectorDb.containsKey(directorName)) {
                currentMovies = movieDirectorDb.get(directorName);
            }
            currentMovies.add(movieName);
            movieDirectorDb.put(directorName, currentMovies);
        }
    }

    public Movie getMovieByName(String movieName) {
        return dbMovie.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return dbDirector.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> moviesList = new ArrayList<>();
        if(movieDirectorDb.containsKey(directorName)){
            moviesList = movieDirectorDb.get(directorName);
        }
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(dbMovie.keySet());
    }

    public void deleteDirectorByName(String directorName) {
        List<String> movies ;
        if(movieDirectorDb.containsKey(directorName)){
            movies = movieDirectorDb.get(directorName);
            for(String movie: movies){
                dbMovie.remove(movie);
            }
            movieDirectorDb.remove(directorName);
        }
        dbDirector.remove(directorName);
    }

    public void deleteAllDirectors() {
        HashSet<String> moviesSet = new HashSet<>();

        for(String director: movieDirectorDb.keySet()){
            moviesSet.addAll(movieDirectorDb.get(director));
        }

        for(String movie: moviesSet){
            dbMovie.remove(movie);
        }
        dbDirector.clear();
    }
}