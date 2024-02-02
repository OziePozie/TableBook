package org.beauty.tablebook.controllers.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.controllers.restaurant.exceptions.UserWithIDNotFoundException;
import org.beauty.tablebook.controllers.restaurant.requests.PostTablesRequestDTO;
import org.beauty.tablebook.models.restaurants.RestaurantDTO;
import org.beauty.tablebook.models.restaurants.RestaurantService;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.beauty.tablebook.models.tables_version.TablesVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@Tag(name = "Работа с ресторанами", description = "Управление ресторанами системы")
public class RestaurantController {

    @Autowired
    private final RestaurantsRepository restaurantsRepository;

    @Autowired
    private final RestaurantService restaurantService;


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
    @Operation(summary = "Получить ресторан по ID")
    public ResponseEntity<RestaurantDTO> getOneRestaurantByID(@PathVariable(value = "id")
                                                                @Parameter(description = "Идентификатор ресторана")
                                                                Long ID){

        if (restaurantsRepository.existsById(ID)){
        Restaurants restaurant = restaurantsRepository.findById(ID).get();

        return ResponseEntity.ok(new RestaurantDTO()
                    .fromEntityToDto(restaurant));
        } else
            return ResponseEntity
                .badRequest()
                .body(null);

    }

    @PostMapping()
    @Operation(summary = "Добавить новый ресторан")
    public ResponseEntity<HttpStatus> postRestaurant(@RequestBody
                                                     RestaurantDTO restaurantDTO){

        try {

            restaurantService.saveRestaurant(restaurantDTO);

        } catch (UserWithIDNotFoundException e){

            return ResponseEntity
                    .of(ProblemDetail.forStatus(HttpStatus.BAD_REQUEST))
                    .build();
        }

        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @PostMapping(value = "/{id}/tables")
    @Operation(summary = "Создать схему ресторана",
            description = "Загрузить новую схему для ресторана")
    public ResponseEntity<HttpStatus>
    postNewTablesSchemeToRestaurant(@RequestBody() PostTablesRequestDTO postTablesRequestDTO,
                                    @PathVariable(name = "id") Long restaurantID){

        TablesVersion tablesVersion = new TablesVersion();

        tablesVersion.setName(postTablesRequestDTO.getVersionName());

        restaurantService.saveTables(postTablesRequestDTO.getTableDTOList(),
                restaurantID,
                tablesVersion);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}


