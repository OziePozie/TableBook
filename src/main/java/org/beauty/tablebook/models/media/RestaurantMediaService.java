package org.beauty.tablebook.models.media;

import org.beauty.tablebook.models.media.reels.Reels;
import org.beauty.tablebook.models.media.reels.ReelsRepository;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class RestaurantMediaService {

    @Autowired
    RestaurantMediaRepository restaurantMediaRepository;
    @Autowired
    ReelsRepository reelsRepository;



    public void saveMedia(String url, Restaurants restaurant){

        RestaurantMedia restaurantMedia =
                restaurantMediaRepository
                        .findRestaurantMediaByRestaurantId(restaurant.getId());

        if (restaurantMedia == null){

            restaurantMedia = new RestaurantMedia();
            restaurantMedia.setRestaurant(restaurant);

        }



        String small = url
                .replaceFirst("width=\\d{2,}&height=\\d{2,}",
                        "width=300&height=200");

        String medium = url
                .replaceFirst("width=\\d{2,}&height=\\d{2,}",
                        "width=600&height=300");

        restaurantMedia.setSmall(small);
        restaurantMedia.setMedium(medium);

        restaurantMediaRepository.save(restaurantMedia);



    }

    public RestaurantMedia getMedia(Restaurants restaurant){

        RestaurantMedia restaurantMedia =
                restaurantMediaRepository
                        .findRestaurantMediaByRestaurantId(restaurant.getId());


        List<Reels> reelsList = reelsRepository.findAllByCreatedAtAfterAndRestaurantId(Date
                .from(Instant.now()
                        .minus(1, ChronoUnit.DAYS)), 1L);
        restaurantMedia.setReelsList(reelsList);

        return restaurantMedia;
    }



}
