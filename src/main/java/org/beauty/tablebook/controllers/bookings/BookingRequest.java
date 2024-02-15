package org.beauty.tablebook.controllers.bookings;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
public class BookingRequest {

    private Long restaurantId;
    private Integer tableId;
    private Long userId;
    private LocalDateTime time;
}
