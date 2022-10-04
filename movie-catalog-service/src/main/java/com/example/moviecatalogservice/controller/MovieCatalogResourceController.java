package com.example.moviecatalogservice.controller;

import com.example.moviecatalogservice.Entity.CatalogItem;
import com.example.moviecatalogservice.Entity.Movie;
import com.example.moviecatalogservice.Entity.Rating;
import com.example.moviecatalogservice.Entity.UserRating;
import com.example.moviecatalogservice.service.MovieInfo;
import com.example.moviecatalogservice.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResourceController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @GetMapping("/get")
    public String getSomeData(){
        return "Helloworld";
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        UserRating ratings = userRatingInfo.getUserRating(userId);
       return  ratings.getRatings().stream().map((rating)->   {
           Movie movie = movieInfo.getMovie(rating.getRating());
          return  new CatalogItem(movie.getTitle(),movie.getOverview(),rating.getRating());
       }
       ).collect(Collectors.toList());

    }

}

