package org.beauty.tablebook.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.beauty.tablebook.models.users.UserDTO;
import org.beauty.tablebook.models.users.Users;
import org.beauty.tablebook.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsersRepository userRepository;

    private static final String SECRET_KEY = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();

        Optional<Users> user = userRepository.findFirstByEmail(email);
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User email doesn't exist");
        } else {
            Users users = user.get();
            if (users.getPassword().equals(password)) {
                String token = generateToken(email);
                return ResponseEntity.ok(new AuthResponse(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO user) {
        String email = user.getEmail();
        if (userRepository.findFirstByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken");
        } else {
            userRepository.save(user.fromDTOtoEntity());
            return ResponseEntity.ok("User registered successfully");
        }
    }

    private String generateToken(String email) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder().setSubject(email).signWith(key).compact();
    }
}
@Data
class AuthRequest {
    private String email;
    private String password;


}
@Data
@AllArgsConstructor
class AuthResponse {
    private String token;


}
