package org.beauty.tablebook.models.media.reels;

import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReelsRepositoryTest {

//    @Autowired
//    ReelsRepository reelsRepository;
//    @Autowired
//    RestaurantsRepository restaurantsRepository;
//    @Test
//    void findAllByCreatedAtAfterAndRestaurantId() {
////        Reels reels = new Reels();
////        reels.setRestaurant(restaurantsRepository.findById(1L).get());
////        reels.setContent("videoContent");
////        reels.setCreatedAt(Date.from(Instant.now()));
////        reelsRepository.save(reels);
//
//        List<Reels> reelsList = reelsRepository
//                .findAllByCreatedAtAfterAndRestaurantId(Date
//                .from(Instant.now()
//                .minus(1, ChronoUnit.DAYS)), 1L);
//        List<ReelsDTOResponse> responseList = new ArrayList<>();
//        for (Reels reels: reelsList){
//            ReelsDTOResponse response = ReelsDTOResponse.fromEntity(reels);
//
//            responseList.add(response);
//        }
//        System.out.println(responseList);
//
//    }
}