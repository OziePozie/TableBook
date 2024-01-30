package org.beauty.tablebook.controllers.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.beauty.tablebook.models.restaurants.RestaurantDTO;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/restaurants")
@Tag(name = "Работа с ресторанами", description = "Управление ресторанами системы")
public class RestaurantController {

    @Autowired
    private final RestaurantsRepository restaurantsRepository;

    public RestaurantController(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @GetMapping()
    @Operation(summary = "Список всех ресторанов",
            description = "Получить список всех ресторанов")
    public ResponseEntity<List<RestaurantDTO>> getRestaurants(){

        List<Restaurants> restaurants = restaurantsRepository.findAll();

        List<RestaurantDTO> restaurantDTOList = new ArrayList<>(restaurants.size());

        for (Restaurants restaurant: restaurants){

            RestaurantDTO restaurantDTO = new RestaurantDTO()
                    .fromEntityToDto(restaurant);

            restaurantDTOList.add(restaurantDTO);

        }



        return ResponseEntity.ok(restaurantDTOList);
    }

    @GetMapping(value = "{id}")
    @Operation()
    public ResponseEntity<RestaurantDTO> getOneRestaurantByID(@PathVariable(value = "id")
                                                                @Parameter(description = "Идентификатор ресторана")
                                                                Long ID){

        if (restaurantsRepository.existsById(ID)){
        Restaurants restaurant = restaurantsRepository.findById(ID).get();

        RestaurantDTO restaurantDTO = new RestaurantDTO();
            ResponseEntity<RestaurantDTO> ok = ResponseEntity.ok(new RestaurantDTO()
                    .fromEntityToDto(restaurant));
            return ok;
        } else return ResponseEntity.badRequest().body(null);

    }

    @PostMapping
    @Operation(summary = "Добавить новый ресторан")
    public ResponseEntity<HttpStatus> postRestaurant(@RequestBody()
                                                     RestaurantDTO restaurantDTO){

        Restaurants restaurant = restaurantDTO.fromDTOtoEntity();

        restaurantsRepository.save(restaurant);

        return ResponseEntity.ok(HttpStatus.CREATED);

    }

}
