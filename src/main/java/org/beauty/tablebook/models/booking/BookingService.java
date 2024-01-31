package org.beauty.tablebook.models.booking;

import org.beauty.tablebook.models.booking.exceptions.TableNotFoundException;
import org.beauty.tablebook.models.booking.exceptions.TableUnavailableException;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.restaurants.RestaurantsRepository;
import org.beauty.tablebook.models.tables.TableRepository;
import org.beauty.tablebook.models.tables.Tables;
import org.beauty.tablebook.models.users.Users;
import org.beauty.tablebook.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TableRepository tableRepository;

    private final RestaurantsRepository restaurantsRepository;

    private final UsersRepository usersRepository;
@Autowired
    public BookingService(BookingRepository bookingRepository, TableRepository tableRepository, RestaurantsRepository restaurantsRepository, UsersRepository usersRepository) {
        this.bookingRepository = bookingRepository;
        this.tableRepository = tableRepository;
        this.restaurantsRepository = restaurantsRepository;
        this.usersRepository = usersRepository;
    }



    @Transactional
    public Booking createBooking(Long restaurantId, Long tableId, Long userId, Date time) {

        Tables table = tableRepository.findById(tableId).orElseThrow(() ->
                new TableNotFoundException(tableId));
        if (!isTableAvailable(table, time)) {
            throw new TableUnavailableException(tableId);
        }

        Users user = usersRepository.findById(userId).get();
        Restaurants restaurant = restaurantsRepository.findById(restaurantId).get();

        Booking booking = new Booking();
        booking.setRestaurant(restaurant);
        booking.setTable(table);
        booking.setUser(user);
        booking.setTime(time);


        return bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserUserID(userId);
    }


    private boolean isTableAvailable(Tables table, Date time) {

        return true;
    }
}

