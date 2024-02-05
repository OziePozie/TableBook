package org.beauty.tablebook.models.restaurants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.booking.Booking;
import org.beauty.tablebook.models.users.Users;
import org.hibernate.annotations.Type;

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
    private String description;
    private Boolean isActive;
    @Lob
    @JsonIgnore
    private String jsonScheme;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Users owner;
    private String logo;


}
