package org.beauty.tablebook.models.restaurants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private Long ownerID;

    public Restaurants fromDTOtoEntity(){



        return new Restaurants.RestaurantsBuilder()
                .address(this.getAddress())
                .city(this.getCity())
                .name(this.getName())
                .build();

    }

    public RestaurantDTO fromEntityToDto(Restaurants restaurant){

        return new RestaurantDTOBuilder()
                .address(restaurant.getAddress())
                .ownerID(restaurant.getOwner().getUserID())
                .name(restaurant.getName())
                .city(restaurant.getCity())
                .build();

    }


}

