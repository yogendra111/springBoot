package com.ksolves.entities;

import com.ksolves.redis.annotations.RedisOneToOne;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stores")
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @RedisOneToOne(key = "Locations")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location")
    private Location location;
}
