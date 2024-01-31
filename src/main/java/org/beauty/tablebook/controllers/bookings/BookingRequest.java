package org.beauty.tablebook.controllers.bookings;

import lombok.Getter;

import java.util.Date;
@Getter
public class BookingRequest {

    private Long restaurantId;
    private Long tableId;
    private Long userId;
    private Date time;
}
