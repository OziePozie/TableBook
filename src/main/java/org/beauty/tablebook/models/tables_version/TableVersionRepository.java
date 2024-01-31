package org.beauty.tablebook.models.tables_version;

import org.beauty.tablebook.models.restaurants.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableVersionRepository extends JpaRepository<TablesVersion, Long> {


    List<TablesVersion> getTablesVersionByRestaurant_IdOrderByCreatedAtDesc(Long restaurantID);



}
