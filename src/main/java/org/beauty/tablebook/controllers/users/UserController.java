package org.beauty.tablebook.controllers.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.controllers.users.exceptions.UserAlreadyExistException;
import org.beauty.tablebook.models.users.UserDTO;
import org.beauty.tablebook.models.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = {
        "http://45.151.144.194:3000",
        "http://45.151.144.194:3000",
        "http://45.151.144.194:80"}
)

@Tag(name = "Работа с пользователями", description = "Управление пользователями системы")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    @Operation(summary = "Создание нового пользователя",
            description = "Создать нового пользователя")
    public ResponseEntity<HttpStatus> postNewUser(@RequestBody()
                                                  UserDTO userDTO){

        try {

            userService.save(userDTO);

        } catch (UserAlreadyExistException e){
            ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
            problemDetail.setDetail("User already exist");
            return ResponseEntity.of(problemDetail)
                    .build();
        }

        return ResponseEntity.ok(HttpStatus.CREATED);

    }



}
