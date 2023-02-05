package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String,Movie> moviedb= new HashMap<>();
    Map<String,Director> directordb= new HashMap<>();
    Map<String, List<String>> director_movie_db=new HashMap<>();


    public void addMovie(Movie movie){
        String movieName=movie.getName();
        moviedb.put(movieName,movie);
    }

    public void addDirector(Director director){
        String directorName=director.getName();
        directordb.put(directorName,director);
    }


    public void addMovieDirectorPair(String movieName, String directorName){
        if(moviedb.containsKey(movieName) && directordb.containsKey(directorName)){
            List<String> list=director_movie_db.getOrDefault(directorName,new ArrayList<String>());
            list.add(movieName);
            director_movie_db.put(directorName,list);
        }
        else{
            //NOT FOUND
            //CAN ADD IMPROVEMENTS HERE
        }
    }

    public Movie getMovieByName( String movieName){
        return moviedb.get(movieName);
    }

    public Director getDirectorByName( String directorName){
        return directordb.get(directorName);
    }


    public List<String> getMoviesByDirectorName(String director){
        return director_movie_db.getOrDefault(director,new ArrayList<String>());
    }


    public List<String> findAllMovies(){
        Set<String> set= moviedb.keySet();
        List<String> moviesList= new ArrayList<>();
        moviesList.addAll(set);
        return moviesList;
    }

    public void deleteDirectorByName(String directorName){
        List<String> directorMovieList=new ArrayList<>();
        if(director_movie_db.containsKey(directorName)){
            directorMovieList=director_movie_db.get(directorName);
            director_movie_db.remove(directorName);
        }
        directordb.remove(directorName);
        for(String movie:directorMovieList){
            moviedb.remove(movie);
        }
    }

    public void deleteAllDirectors(){
        Set<String> listOfDirectors=director_movie_db.keySet();
        directordb.clear();
        for(String director:listOfDirectors){
            List<String> listOfDirectorMovies=director_movie_db.get(director);
            director_movie_db.remove(director);
            for(String movie:listOfDirectorMovies){
                moviedb.remove(movie);
            }
        }
    }
}