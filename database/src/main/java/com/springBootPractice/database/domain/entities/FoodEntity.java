package com.springBootPractice.database.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "foods")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_id_seq")
    private Long id;

    private String name;

    private Long calories;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chef_id")
    private ChefEntity chef;

}
