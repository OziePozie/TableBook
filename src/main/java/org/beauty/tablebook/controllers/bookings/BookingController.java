package org.beauty.tablebook.controllers.bookings;

import org.beauty.tablebook.models.booking.Booking;
import org.beauty.tablebook.models.booking.BookingService;
import org.beauty.tablebook.models.booking.exceptions.TableNotFoundException;
import org.beauty.tablebook.models.booking.exceptions.TableUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest request) {
        try {
            Booking booking = bookingService.createBooking(request.getRestaurantId(),
                    request.getTableId(),
                    request.getUserId(),
                    request.getTime());
            return ResponseEntity.ok("Booking created successfully with id: " + booking.getId());
        } catch (TableNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table with id " + request.getTableId() + " not found");
        } catch (TableUnavailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Table with id " + request.getTableId() + " is unavailable at the specified time");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create booking");
        }
    }



}
