package org.beauty.tablebook.models.restaurants;

import org.beauty.tablebook.controllers.restaurant.exceptions.UserWithIDNotFoundException;
import org.beauty.tablebook.models.media.RestaurantMediaService;
import org.beauty.tablebook.models.media.reels.Reels;
import org.beauty.tablebook.models.media.reels.ReelsRepository;
import org.beauty.tablebook.models.tables.TableDTO;
import org.beauty.tablebook.models.tables.TableRepository;
import org.beauty.tablebook.models.tables.Tables;
import org.beauty.tablebook.models.tables_version.TableVersionRepository;
import org.beauty.tablebook.models.tables_version.TablesVersion;
import org.beauty.tablebook.models.users.Users;
import org.beauty.tablebook.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantsRepository restaurantRepository;
    private final UsersRepository userRepository;

    private final TableRepository tableRepository;

    private final TableVersionRepository tableVersionRepository;
    private final ReelsRepository reelsRepository;
    @Autowired
    RestaurantMediaService restaurantMediaService;

    public RestaurantService(RestaurantsRepository restaurantRepository, UsersRepository userRepository, TableRepository tableRepository, TableVersionRepository tableVersionRepository, ReelsRepository reelsRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
        this.tableVersionRepository = tableVersionRepository;
        this.reelsRepository = reelsRepository;
    }


    public void saveRestaurant(RestaurantDTO restaurantDTO) {

        Optional<Users> optionalUser = userRepository.findById(restaurantDTO.getOwnerID());

        if (optionalUser.isPresent()) {
            Users owner = optionalUser.get();

            Restaurants restaurant = restaurantDTO.fromDTOtoEntity();

            restaurant.setOwner(owner);

            restaurant = restaurantRepository.save(restaurant);

            restaurantMediaService.saveMedia(restaurantDTO.getUrl(), restaurant);

        } else {
            throw new UserWithIDNotFoundException(restaurantDTO.getOwnerID());
        }
    }

    public void saveTables(List<TableDTO> tableDTOList,
                           Long restaurantID,
                           TablesVersion tablesVersion){

        List<Tables> tablesList = new ArrayList<>(tableDTOList.size());
        Restaurants restaurant;

        TablesVersion oldTablesVersion = tableVersionRepository
                .findFirstByRestaurantIdAndIsUsingIsTrue(restaurantID);
        if (oldTablesVersion != null) {
            oldTablesVersion.setIsUsing(false);

            tableVersionRepository.save(oldTablesVersion);
        }
        if (restaurantRepository.existsById(restaurantID)) {

            restaurant = restaurantRepository.findById(restaurantID).get();

            tablesVersion.setCreatedAt(Date.from(Instant.now()));

            tablesVersion.setRestaurant(restaurant);

            tablesVersion.setIsUsing(true);

            tableVersionRepository.save(tablesVersion);

        } else
            throw new RuntimeException();


        for(TableDTO tableDTO:tableDTOList){

            Tables table = Tables.fromDTO(tableDTO);

            table.setRestaurant(restaurant);



            tablesList.add(table);

        }


        tableRepository.saveAll(tablesList);

    }



    public void saveTables(List<Integer> list, Long restID){

        Restaurants rest = restaurantRepository.findById(restID).get();

        tableRepository.deleteAllByRestaurantId(restID);

        for (Integer tableId:list){

            Tables table = new Tables();
            table.setRestaurant(rest);
            table.setTableId(tableId);

            tableRepository.save(table);
        }

    }
    public void saveReels(Reels reels, Long restID){
        Restaurants rest = restaurantRepository.findById(restID).get();
        reels.setRestaurant(rest);
        reelsRepository.save(reels);



    }

    public void saveScheme(String json, Long restID){
        Restaurants restaurants = restaurantRepository.findById(restID).get();
        restaurants.setJsonScheme(json);
        restaurantRepository.save(restaurants);


    }

    public Restaurants getRestaurantByID(Long restID) {

        Restaurants restaurants = restaurantRepository.findById(restID).get();

        return restaurants;

    }

}
