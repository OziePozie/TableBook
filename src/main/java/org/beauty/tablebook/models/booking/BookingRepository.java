package org.beauty.tablebook.models.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserUserID(Long userId);

    List<Booking> findByRestaurantId(Long restId);

    @Query("SELECT b FROM Booking b WHERE b.time BETWEEN :startTime AND :endTime AND b.restaurant.id = :restId")
    List<Booking> findAllByRestIdAndTime(@Param("startTime") LocalDateTime startTime,
                                         @Param("endTime") LocalDateTime endTime,
                                         Long restId);
}
