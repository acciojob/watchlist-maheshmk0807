package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String,Movie> moviedb= new HashMap<>();
    Map<String,Director> directordb= new HashMap<>();
    Map<String, List<String>> director_movie_db=new HashMap<>();


    public String addMovie( Movie movie){
        String movieName=movie.getName();
        moviedb.put(movieName,movie);
        return "Movie "+movieName+" added Successfully";
    }

    public String addDirector( Director director){
        String directorName=director.getName();
        directordb.put(directorName,director);
        return "Director "+directorName+" added Successfully";
    }


    public String addMovieDirectorPair( String movieName,String directorName){
        if(moviedb.containsKey(movieName) && directordb.containsKey(directorName)){
            List<String> list=director_movie_db.getOrDefault(directorName,new ArrayList<String>());
            list.add(movieName);
            director_movie_db.put(directorName,list);
            return "Director -"+directorName+" movie-"+movieName+" added Successfully";
        }
        else
            return "details not found.Write correct details";
    }

    public Movie getMovieByName( String movieName){
        Movie movie=moviedb.get(movieName);
        return movie;
    }

    public Director getDirectorByName( String directorName){
        Director director=directordb.get(directorName);
        return director;
    }


    public List<String> getMoviesByDirectorName(String director){
        List<String> moviesList=director_movie_db.getOrDefault(director,new ArrayList<String>());
        return moviesList;
    }


    public List<String> findAllMovies(){
        Set<String> set= moviedb.keySet();

        List<String> moviesList= new ArrayList<>();
        for(String s:set){
            moviesList.add(s);
        }
        return moviesList;
    }

    public String deleteDirectorByName( String directorName){
        List<String> directorMovieList=new ArrayList<>();
        if(director_movie_db.containsKey(directorName)){
            directorMovieList=director_movie_db.get(directorName);
            director_movie_db.remove(directorName);
        }

        if(directordb.containsKey(directorName))
            directordb.remove(directorName);

        for(String movie:directorMovieList){
            if(moviedb.containsKey(movie))
                moviedb.remove(movie);
        }
        return "Director and its movies removed successfully";
    }

    public String deleteAllDirectors(){
        Set<String> listOfDirectors=director_movie_db.keySet();
        directordb.clear();
        for(String director:listOfDirectors){
            List<String> listOfDirectorMovies=director_movie_db.get(director);
            director_movie_db.remove(director);

            for(String movie:listOfDirectorMovies){
                if(moviedb.containsKey(movie))
                    moviedb.remove(movie);
            }

        }
        return "All Director and their all movies removed successfully";
    }

}