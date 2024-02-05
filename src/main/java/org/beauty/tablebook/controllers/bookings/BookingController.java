package org.beauty.tablebook.controllers.bookings;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.beauty.tablebook.models.booking.Booking;
import org.beauty.tablebook.models.booking.BookingService;
import org.beauty.tablebook.models.booking.exceptions.TableNotFoundException;
import org.beauty.tablebook.models.booking.exceptions.TableUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin(origins = {
        "http://45.151.144.194:3000",
        "http://45.151.144.194:3000",
        "http://45.151.144.194:80"}
)

@Tag(name = "Работа с резервированиями", description = "Работа с резервацией столов")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    @ApiResponses({
            @ApiResponse (responseCode = "200", description = "Успешное создание резерва"),
            @ApiResponse (responseCode = "404", description = "Стол недоступен"),
            @ApiResponse (responseCode = "400", description = "Стола с таким Id не существует")
            })
    public ResponseEntity<HttpStatus> createBooking(@RequestBody BookingRequest request) {
        try {
            Booking booking = bookingService
                    .createBooking(request.getRestaurantId(),
                    request.getTableId(),
                    request.getUserId(),
                    request.getTime());
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (TableNotFoundException e) {
            return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.NOT_FOUND)).build();
                    //ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table with id " + request.getTableId() + " not found");
        } catch (TableUnavailableException e) {
            return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)).build();
//                    ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Table with id " + request.getTableId() + " is unavailable at the specified time");
        } catch (Exception e) {
            return ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)).build();
//                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create booking");
        }
    }

    @GetMapping("/restaurants/{restId}/tables/{tableId}")
    public ResponseEntity<List<BookingResponseByTable>> getBookingsByTable(@PathVariable(name = "tableId") Integer tableId,
                                                     @PathVariable Long restId){

        try {
            List<Booking> bookings = bookingService.getBookingsByRestaurant(restId);

            List<BookingResponseByTable> listOfBookingOnTable = new ArrayList<>();

            for (Booking booking: bookings){

                if (Objects.equals(booking.getTable().getTableId(), tableId)){

                    BookingResponseByTable bookingResponseByTable =
                            new BookingResponseByTable(booking.getTime());

                    listOfBookingOnTable.add(bookingResponseByTable);

                }

            }
            return ResponseEntity.ok(listOfBookingOnTable);
        }  catch (TableNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping("/restaurants/{restId}")
    public ResponseEntity<List<BookingResponseByRestaurant>> getBookingsByRestaurant(@PathVariable Long restId){
        try {
            List<BookingResponseByRestaurant> bookingResponseByRestaurants = new ArrayList<>();
            List<Booking> bookings = bookingService.getBookingsByRestaurant(restId)
                    .stream()
                    .filter(booking -> booking.getTime()
                            .after(Date.from(Instant.now())))
                    .toList();

            for(Booking booking: bookings) {

                BookingResponseByRestaurant book =
                        new BookingResponseByRestaurant();
                book.setTableId(booking.getTable().getTableId());
                book.setTime(booking.getTime());
                bookingResponseByRestaurants.add(book);

            }
            return ResponseEntity.ok(bookingResponseByRestaurants);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
}


}
@Setter
@Getter
class BookingResponseByRestaurant{

    Integer tableId;

    Date time;


}