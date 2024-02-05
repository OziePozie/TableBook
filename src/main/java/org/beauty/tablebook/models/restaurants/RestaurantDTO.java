package org.beauty.tablebook.models.restaurants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.media.RestaurantMedia;

@Data
@Builder
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Long ID;
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private Long ownerID;
    @NotBlank
    private String description;
//    private RestaurantMedia media;
    private String url;

    public Restaurants fromDTOtoEntity(){

        return new Restaurants.RestaurantsBuilder()
                .id(this.ID)
                .address(this.getAddress())
                .city(this.getCity())
                .name(this.getName())
                .logo(this.getUrl())
                .description(this.description)
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
                .description(restaurant.getDescription())
                .build();

    }


}

