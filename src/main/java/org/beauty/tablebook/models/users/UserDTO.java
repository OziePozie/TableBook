package org.beauty.tablebook.models.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class UserDTO {


    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;



    private Timestamp birthdayDate;

    @NotBlank
    private String phoneNumber;


    public UserDTO fromEntityToDTO(Users user){

        return UserDTO.builder()
                .firstName(user.getUserFirstName())
                .lastName(user.getUserLastName())
                .birthdayDate(user.getBirthdayDate())
                .email(user.getEmail())
                .password(user.getPasswordHash())
                .phoneNumber(user.getPhoneNumber())
                .build();

    }

    public Users fromDTOtoEntity(){
        Users users = new Users.UsersBuilder()
                .userFirstName(this.getFirstName())
                .userLastName(this.getLastName())
                .birthdayDate(this.getBirthdayDate())
                .email(this.getEmail())
                .passwordHash(this.getPassword())
                .phoneNumber(this.getPhoneNumber())
                .build();
        return users;


    }

}
