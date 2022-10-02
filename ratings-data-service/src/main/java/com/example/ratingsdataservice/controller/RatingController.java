package com.example.ratingsdataservice.controller;

import com.example.ratingsdataservice.models.Rating;
import com.example.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getRating(@PathVariable("userId")String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("3234",5),
                new Rating("3454",2)
        );
        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        return userRating;
    }
}
