package org.beauty.tablebook.models.restaurants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.users.Users;

@Data
@Builder
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private String name;
    private String city;
    private String address;
    private Long ownerID;

    public Restaurants fromDTOtoEntity(){

        Users user = new Users();

        user.setUserID(this.getOwnerID());

        return new Restaurants.RestaurantsBuilder()
                .address(this.getAddress())
                .owner(user)
                .city(this.getCity())
                .name(this.getName())
                .build();

    }

    public RestaurantDTO fromEntityToDto(Restaurants restaurant){

        return new RestaurantDTOBuilder()
                .address(restaurant.getAddress())
                .ownerID(getOwnerID())
                .name(restaurant.getName())
                .city(restaurant.getCity())
                .build();

    }


}

