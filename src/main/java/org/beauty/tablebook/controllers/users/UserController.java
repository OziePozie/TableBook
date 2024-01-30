package org.beauty.tablebook.controllers.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.beauty.tablebook.models.restaurants.RestaurantDTO;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.users.UserDTO;
import org.beauty.tablebook.models.users.Users;
import org.beauty.tablebook.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "Работа с пользователями", description = "Управление пользователями системы")
public class UserController {

    private final UsersRepository usersRepository;

    public UserController(@Autowired UsersRepository usersRepository) {

        this.usersRepository = usersRepository;
    }

    @GetMapping()
    @Operation(summary = "Список пользователей",
            description = "Получить список всех пользователей")
    public ResponseEntity<List<Users>> getUsers(){
        return ResponseEntity.ok(usersRepository.findAll());
    }

    @PostMapping()
    @Operation
    public ResponseEntity<HttpStatus> postNewUser(@RequestBody()
             UserDTO userDTO){

        Users user = new Users();
        user = userDTO.fromDTOtoEntity();
        usersRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
