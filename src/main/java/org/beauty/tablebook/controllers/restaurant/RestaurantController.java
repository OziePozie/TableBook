package org.beauty.tablebook.controllers.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<List<Restaurants>> getRestaurants(){
        return ResponseEntity.ok(restaurantsRepository.findAll());
    }

    @GetMapping(value = "{id}")
    @Operation()
    public ResponseEntity<Restaurants> getOneRestaurantByID(@PathVariable(value = "id")
                                                                @Parameter(description = "Идентификатор ресторана")
                                                                Long ID){

        if (restaurantsRepository.existsById(ID)){
            return ResponseEntity.ok(restaurantsRepository.findById(ID).get());
        } else return ResponseEntity.badRequest().body(null);

    }

}
