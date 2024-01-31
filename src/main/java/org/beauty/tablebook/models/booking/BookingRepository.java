package org.beauty.tablebook.models.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserUserID(Long userId);

    List<Booking> findByRestaurantId(Long restId);
}
