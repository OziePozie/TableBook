package org.beauty.tablebook.controllers.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.beauty.tablebook.models.users.Users;
import org.beauty.tablebook.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
