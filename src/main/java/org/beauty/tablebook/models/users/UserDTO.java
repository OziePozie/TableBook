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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

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


    private Boolean isAdmin;
    private LocalDate birthdayDate;

    @NotBlank
    private String phoneNumber;


    public UserDTO fromEntityToDTO(Users user){

        return UserDTO.builder()
                .firstName(user.getUserFirstName())
                .lastName(user.getUserLastName())
                .birthdayDate(user.getBirthdayDate())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())

                .build();

    }

    public Users fromDTOtoEntity(){
        Users users = new Users.UsersBuilder()
                .userFirstName(this.getFirstName())
                .userLastName(this.getLastName())
                .birthdayDate(this.getBirthdayDate())
                .email(this.getEmail())
                .password(this.getPassword())
                .phoneNumber(this.getPhoneNumber())
                .isAdmin(this.getIsAdmin())
                .build();
        return users;


    }

}
