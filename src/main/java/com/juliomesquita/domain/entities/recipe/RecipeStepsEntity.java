package com.juliomesquita.domain.entities.recipe;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_recipe_steps")
public class RecipeStepsEntity extends PanacheEntity {

   private Integer executionOrder;

   private String description;

   private Integer durationTime;

   @ManyToOne
   @JoinColumn(name = "recipe_id", referencedColumnName = "id")
   private RecipeAggregate recipe;
}
