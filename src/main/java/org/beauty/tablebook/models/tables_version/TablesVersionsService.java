package org.beauty.tablebook.models.tables_version;

import org.beauty.tablebook.models.restaurants.RestaurantService;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesVersionsService {


    private final TableVersionRepository tableVersionRepository;



    public TablesVersionsService(@Autowired TableVersionRepository tableVersionRepository) {
        this.tableVersionRepository = tableVersionRepository;

    }


    public List<TablesVersion> getAllVersionByRestID(Long restID){



        return tableVersionRepository
                .getTablesVersionByRestaurant_IdOrderByCreatedAtDesc(restID);

    }


}
