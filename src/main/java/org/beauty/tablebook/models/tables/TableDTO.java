package org.beauty.tablebook.models.tables;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.restaurants.RestaurantDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableDTO {

    private String tableName;
    private int capacity;
    private int positionX;
    private int positionY;

    public static TableDTO fromEntity(Tables table) {
        return TableDTO.builder()
//                .id(table.getId())
//                .restaurantId(table.getRestaurant().getId())
                .tableName(table.getTableName())
                .capacity(table.getCapacity())
                .positionX(table.getPositionX())
                .positionY(table.getPositionY())
                .build();

    }


}
