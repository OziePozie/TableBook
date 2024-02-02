package org.beauty.tablebook.models.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantMediaRepository extends JpaRepository<RestaurantMedia, Long> {

     RestaurantMedia findRestaurantMediaByRestaurantId(Long id);

}
