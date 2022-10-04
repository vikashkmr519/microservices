package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.Entity.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackMovie")
    public Movie getMovie(int rating){
        return restTemplate.getForObject("http://movie-info-service/movies/"+rating, Movie.class);
    }

    public Movie getFallbackMovie(int rating){
        Movie movie = new Movie();
        movie.setId(2123);
        return movie;
    }
}
