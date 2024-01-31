package org.beauty.tablebook.models.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Tables, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tables WHERE restaurant_id = ?1 AND version_id= ?2")
    List<Tables> getByRestaurantID(Long restaurantID, Long versionID);

    List<Tables> getAllByTablesVersion_Id(Long versionID);
}
