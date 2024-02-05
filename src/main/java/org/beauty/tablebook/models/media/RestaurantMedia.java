package org.beauty.tablebook.models.media;

import jakarta.persistence.*;
import lombok.Data;
import org.beauty.tablebook.models.media.reels.Reels;
import org.beauty.tablebook.models.restaurants.Restaurants;

import java.util.List;

@Entity
@Data
public class RestaurantMedia {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String small;
    private String medium;
    private String big;
    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Reels> reelsList;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurants restaurant;


}
