package org.beauty.tablebook.models.tables;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;

    private final RestaurantsRepository restaurantsRepository;

    public List<TableDTO> getAllTablesByRestaurantIDAndVersionID(Long restaurantID, Long versionID){
        List<Tables> tables = tableRepository.getByRestaurantID(restaurantID, versionID);

        List<TableDTO> tableDTOList = new ArrayList<>();

        for (Tables table: tables){

            TableDTO tableDTO = TableDTO.fromEntity(table);

            tableDTOList.add(tableDTO);

        }

        return tableDTOList;

    }

    public Tables getTableByID(Long tableID){

        return tableRepository.findById(tableID).get();

    }

    public void saveTableToRestaurant(Tables table, Long restID){

        Restaurants rest = restaurantsRepository.findById(restID).get();

        table.setRestaurant(rest);

        tableRepository.save(table);



    }


    public List<TableDTO> getAllTablesByVersionID(Long versionID) {

        List<Tables> tablesList = tableRepository.getAllByTablesVersion_Id(versionID);

        List<TableDTO> tableDTOList = new ArrayList<>(tablesList.size());

        for (Tables table:tablesList){

            TableDTO tableDTO = TableDTO.fromEntity(table);

            tableDTOList.add(tableDTO);

        }
        return tableDTOList;
    }
}
