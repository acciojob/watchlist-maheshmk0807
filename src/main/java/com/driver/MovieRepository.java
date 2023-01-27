package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {


    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository() {
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public String addMovie(Movie movie) {
        movieMap.put(movie.getName(),movie);
        return "Movie added successfully!";

    }

    public String addDirector(Director director) {
        directorMap.put(director.getName(),director);
        return "Director added successfully!";
    }

    public String addMovieDirectorPair(String movie, String director) {
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){

           // movieMap.put(movie, movieMap.get(movie));
           // directorMap.put(director, directorMap.get(director));

            List<String> currentMovies;
            if(directorMovieMapping.containsKey(director))
                currentMovies = directorMovieMapping.get(director);
            else
                currentMovies = new ArrayList<String>();

            currentMovies.add(movie);
            directorMovieMapping.put(director, currentMovies);

            return "Pair added successfully !";
        }
        else
            return "Not_Found";

    }

    public Movie getMovieByName(String movieName) {
        if(movieMap.containsKey(movieName)){
            System.out.println("You found movie and returned");
            return movieMap.get(movieName);
        }
        else{
            System.out.println("Movie not found returned null");
            System.out.println("Required : "+movieName);
            System.out.println("But you have :");
            for(String s : movieMap.keySet())
                System.out.println(s);
            return null;
        }
    }

    public Director getDirectorByName(String directorName) {
        return directorMap.getOrDefault(directorName, null);
    }

    public List getMoviesByDirectorName(String directorName) {
        for(String s : directorMovieMapping.keySet()){
            if(s.equals(directorName))
                return directorMovieMapping.get(s);
        }
        return new ArrayList<>();
    }

    public List findAllMovies() {
        List movies = new ArrayList<>();
        for(String s : movieMap.keySet())
            movies.add(s);
        return movies;
    }

    public boolean deleteDirectorByName(String directorName) {
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
            return true;
        }
        else
            return false;
    }

    public boolean deleteAllDirectors() {
        directorMap.clear();
        return true;
    }
}
