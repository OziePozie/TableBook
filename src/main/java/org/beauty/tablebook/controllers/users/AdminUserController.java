package org.beauty.tablebook.controllers.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.models.users.UserDTO;
import org.beauty.tablebook.models.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/users")
@Tag(name = "Работа с пользователями (Админка)", description = "Полное управление пользователями системы")
@AllArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @GetMapping()
    @Operation(summary = "Список пользователей",
            description = "Получить список всех пользователей")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(userService.findAllUsersDTO());
    }


}
