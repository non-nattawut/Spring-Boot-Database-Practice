package com.springBootPractice.database.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chefs")
public class ChefEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chef_id_seq")
    @GenericGenerator(
            name = "chef_id_seq",
            parameters = {
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
    })
    private Long id;

    private String name;

    private Long age;

}
