package org.beauty.tablebook.models.restaurants;

import org.beauty.tablebook.controllers.restaurant.exceptions.UserWithIDNotFoundException;
import org.beauty.tablebook.models.users.Users;
import org.beauty.tablebook.models.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantsRepository restaurantRepository;
    private final UsersRepository userRepository;

    public RestaurantService(RestaurantsRepository restaurantRepository, UsersRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public void saveRestaurant(RestaurantDTO restaurantDTO) {

        Optional<Users> optionalUser = userRepository.findById(restaurantDTO.getOwnerID());

        if (optionalUser.isPresent()) {
            Users owner = optionalUser.get();

            Restaurants restaurant = restaurantDTO.fromDTOtoEntity();
            restaurant.setOwner(owner);
            restaurantRepository.save(restaurant);
        } else {
            throw new UserWithIDNotFoundException(restaurantDTO.getOwnerID());
        }
    }
}
