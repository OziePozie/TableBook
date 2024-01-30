package org.beauty.tablebook.models.restaurants;

import jakarta.persistence.*;
import lombok.Data;
import org.beauty.tablebook.models.users.Users;

@Entity
@Data
@Table(name = "restaurants")
public class Restaurants {

    @Id
    private Long id;
    private String name;
    private String city;
    private String address;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Users owner;








}
