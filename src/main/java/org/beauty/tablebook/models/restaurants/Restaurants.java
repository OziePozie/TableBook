package org.beauty.tablebook.models.restaurants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.users.Users;

@Entity
@Data
@Table(name = "restaurants")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Users owner;
    private String logo;


}
