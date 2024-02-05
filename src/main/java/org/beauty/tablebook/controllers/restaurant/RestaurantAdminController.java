package org.beauty.tablebook.controllers.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.controllers.restaurant.requests.PostTablesRequestDTO;
import org.beauty.tablebook.models.media.reels.ReelDTOReq;
import org.beauty.tablebook.models.media.reels.Reels;
import org.beauty.tablebook.models.restaurants.RestaurantService;
import org.beauty.tablebook.utils.JSONParserToSaveTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/owner/restaurants/")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@Tag(name = "Работа с ресторанами (Владелец)", description = "Управление ресторанами системы")
public class RestaurantAdminController {
    @Autowired
    private final RestaurantService restaurantService;
    @PostMapping(value = "/{id}/tables")
    @Operation(summary = "Создать схему ресторана",
            description = "Загрузить новую схему для ресторана")
    public ResponseEntity<HttpStatus>
    postNewTablesSchemeToRestaurant(@RequestBody() PostTablesRequestDTO postTablesRequestDTO,
                                    @PathVariable(name = "id") Long restaurantID){

        String json = postTablesRequestDTO.getFile();
        List<Integer> tableIDsList = JSONParserToSaveTables.parse(json);

        restaurantService.saveTables(tableIDsList, restaurantID);
        restaurantService.saveScheme(json, restaurantID);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping(value = "/{id}/media")
    @Operation(summary = "Загрузка медиа",
            description = "Загрузить видео для рилсов")
    public ResponseEntity<HttpStatus>
    postNewReelsToRestaurant(@RequestBody() ReelDTOReq reelDTOReq,
                             @PathVariable(name = "id") Long restaurantID){

        Reels reels = reelDTOReq.toEntity();


        restaurantService.saveReels(reels, restaurantID);


        return ResponseEntity.ok(HttpStatus.OK);
    }
}
