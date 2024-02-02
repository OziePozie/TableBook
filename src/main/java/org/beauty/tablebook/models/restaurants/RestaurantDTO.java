package org.beauty.tablebook.models.restaurants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    @NotBlank
    private Long ID;
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private Long ownerID;
    private String url;

    public Restaurants fromDTOtoEntity(){

        return new Restaurants.RestaurantsBuilder()
                .id(this.ID)
                .address(this.getAddress())
                .city(this.getCity())
                .name(this.getName())
                .logo(this.getUrl())
                .build();

    }

    public RestaurantDTO fromEntityToDto(Restaurants restaurant){

        return new RestaurantDTOBuilder()
                .ID(restaurant.getId())
                .address(restaurant.getAddress())
                .ownerID(restaurant.getOwner().getUserID())
                .name(restaurant.getName())
                .city(restaurant.getCity())
                .url(restaurant.getLogo())
                .build();

    }


}

