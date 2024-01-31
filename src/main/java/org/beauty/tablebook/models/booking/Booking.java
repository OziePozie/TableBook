package org.beauty.tablebook.models.booking;

import jakarta.persistence.*;
import lombok.Data;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.tables.Tables;
import org.beauty.tablebook.models.users.Users;

import java.util.Date;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurants restaurant;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables table;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;


}
