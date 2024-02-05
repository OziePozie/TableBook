package org.beauty.tablebook.controllers.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.controllers.restaurant.exceptions.UserWithIDNotFoundException;
import org.beauty.tablebook.models.restaurants.RestaurantDTO;
import org.beauty.tablebook.models.restaurants.RestaurantService;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/restaurants/")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@Tag(name = "Работа с ресторанами (Админ-панель)", description = "Полное управление ресторанами системы")
public class AdminController {

    @Autowired
    private final RestaurantService restaurantService;

    @PostMapping()
    @Operation(summary = "Добавить новый ресторан")
    public ResponseEntity<HttpStatus> postRestaurant(@RequestBody
                                                     RestaurantDTO restaurantDTO) {

        try {

            restaurantService.saveRestaurantWithMedia(restaurantDTO);

        } catch (UserWithIDNotFoundException e) {

            return ResponseEntity
                    .of(ProblemDetail.forStatus(HttpStatus.BAD_REQUEST))
                    .build();
        }

        return ResponseEntity.ok(HttpStatus.CREATED);

    }
    @DeleteMapping
    @Operation(summary = "Удалить ресторан")
    public ResponseEntity<HttpStatus> deleteRestaurant(@RequestBody
                                                       Long id) {

        try {

            Restaurants restaurant = restaurantService.getRestaurantByID(id);

            restaurant.setIsActive(Boolean.FALSE);

            restaurantService.saveRestaurant(restaurant);
        } catch (UserWithIDNotFoundException e) {

            return ResponseEntity
                    .of(ProblemDetail.forStatus(HttpStatus.BAD_REQUEST))
                    .build();
        }

        return ResponseEntity.ok(HttpStatus.OK);

    }
}
