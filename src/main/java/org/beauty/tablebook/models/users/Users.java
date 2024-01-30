package org.beauty.tablebook.models.users;

import jakarta.persistence.*;
import lombok.Data;
import org.beauty.tablebook.models.restaurants.Restaurants;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class Users {


    @Id
    @Column(name = "id")
    private Long userID;
    @Column(name = "first_name")
    private String userFirstName;
    private String userLastName;
    private String email;
    private String passwordHash;
    private Timestamp birthdayDate;
    private String phoneNumber;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private List<Restaurants> restaurants = new ArrayList<>();




}
