package org.beauty.tablebook.models.restaurants;

import org.beauty.tablebook.controllers.restaurant.exceptions.UserWithIDNotFoundException;
import org.beauty.tablebook.models.tables.TableDTO;
import org.beauty.tablebook.models.tables.TableRepository;
import org.beauty.tablebook.models.tables.Tables;
import org.beauty.tablebook.models.tables_version.TableVersionRepository;
import org.beauty.tablebook.models.tables_version.TablesVersion;
import org.beauty.tablebook.models.users.Users;
import org.beauty.tablebook.models.users.UsersRepository;
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

    public RestaurantService(RestaurantsRepository restaurantRepository, UsersRepository userRepository, TableRepository tableRepository, TableVersionRepository tableVersionRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
        this.tableVersionRepository = tableVersionRepository;
    }


    public void saveRestaurant(RestaurantDTO restaurantDTO) {

        Optional<Users> optionalUser = userRepository.findById(restaurantDTO.getOwnerID());

        if (optionalUser.isPresent()) {
            Users owner = optionalUser.get();

            Restaurants restaurant = restaurantDTO.fromDTOtoEntity();

            restaurant.setOwner(owner);

            restaurantRepository.save(restaurant);
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

            table.setTablesVersion(tablesVersion);

            tablesList.add(table);

        }


        tableRepository.saveAll(tablesList);

    }
}
