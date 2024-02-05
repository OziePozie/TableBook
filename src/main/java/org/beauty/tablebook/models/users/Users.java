package org.beauty.tablebook.models.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.restaurants.Restaurants;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @Column(name = "first_name")
    private String userFirstName;
    private String userLastName;
    private String email;
    private String password;
    private LocalDate birthdayDate;
    private String phoneNumber;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private List<Restaurants> restaurants = new ArrayList<>();




}
