package org.beauty.tablebook.models.tables;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.tables_version.TablesVersion;

@Entity
@Data
@Table(name = "tables")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurants restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_id", nullable = false)
    private TablesVersion tablesVersion;

    private String tableName;
    private int capacity;
    private int positionX;
    private int positionY;
    public static Tables fromDTO(TableDTO tableDTO) {
        Tables table = new Tables();
//        table.setId(tableDTO.getId());
        table.setTableName(tableDTO.getTableName());
        table.setCapacity(tableDTO.getCapacity());
        table.setPositionX(tableDTO.getPositionX());
        table.setPositionY(tableDTO.getPositionY());
        return table;
    }
}