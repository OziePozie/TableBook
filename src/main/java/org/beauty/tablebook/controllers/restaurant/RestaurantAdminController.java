package org.beauty.tablebook.controllers.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/owner/restaurants")
@CrossOrigin(origins = {
        "http://45.151.144.194:3000",
        "http://45.151.144.194:3000",
        "http://45.151.144.194:80"}
)
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@Tag(name = "Работа с ресторанами (Владелец)", description = "Управление ресторанами системы")
public class RestaurantAdminController {
    @Autowired
    private final RestaurantService restaurantService;

    @Autowired
    HttpServletRequest req;
    @PostMapping(value = "/{id}/tables")
    @Operation(summary = "Создать схему ресторана",
            description = "Загрузить новую схему для ресторана")
    @SecurityRequirement(name = "bearerAuth")
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
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "OK",
            useReturnTypeSchema = true)
    public ResponseEntity<HttpStatus>
    postNewReelsToRestaurant(@RequestBody() ReelDTOReq reelDTOReq,
                             @PathVariable(name = "id") Long restaurantID){

        Reels reels = reelDTOReq.toEntity();

        String userId = (String) req.getAttribute("userId");
        System.out.println("EMAIL ====== " + userId);

        restaurantService.saveReels(reels, restaurantID);


        return ResponseEntity.ok(HttpStatus.OK);
    }
}
