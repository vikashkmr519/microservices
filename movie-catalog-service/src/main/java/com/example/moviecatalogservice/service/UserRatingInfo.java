package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.Entity.Rating;
import com.example.moviecatalogservice.Entity.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRatingInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
                threadPoolKey = "moiveInfoPool",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value="20"),
                    @HystrixProperty(name="maxQueueSize",value="10")
            }
    )
    public UserRating getUserRating(String userId){
        return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId,UserRating.class);

    }

    public UserRating getFallbackUserRating( String userId){
        UserRating userRating = new UserRating();
        userRating.setRatings(Arrays.asList(
                new Rating("0",4)
        ));
        return userRating;

    }
}
