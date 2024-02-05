package org.beauty.tablebook.models.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TableRepository extends JpaRepository<Tables, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tables WHERE restaurant_id = ?1")
    List<Tables> getByRestaurantID(Long restaurantID, Long versionID);
    @Modifying
    void deleteAllByRestaurantId(Long restID);

    Tables getFirstByTableId(Integer tableID);

//    List<Tables> getAllByTablesVersion_Id(Long versionID);
}
