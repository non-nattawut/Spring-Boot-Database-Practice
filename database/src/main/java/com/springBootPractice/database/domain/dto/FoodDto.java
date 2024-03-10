package com.springBootPractice.database.domain.dto;

import com.springBootPractice.database.domain.entities.ChefEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDto {

    private Long id;

    private String name;

    private Long calories;

    private ChefEntity chef;

}
