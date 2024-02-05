package org.beauty.tablebook.models.media.reels;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Long> {

    List<Reels> findAllByCreatedAtAfterAndRestaurantId(Date date, Long restId);


}
