package org.beauty.tablebook.models.media;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.beauty.tablebook.models.restaurants.Restaurants;

@Entity
@Data
public class RestaurantMedia {

    @Id
    private Long id;
    private String small;
    private String medium;
    private String big;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurants restaurant;


}
