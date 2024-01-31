package org.beauty.tablebook.models.tables_version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.beauty.tablebook.models.restaurants.Restaurants;

import java.util.Date;

@Entity
@Data
public class TablesVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurants restaurant;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Boolean isUsing;

}
