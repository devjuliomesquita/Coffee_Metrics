package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.enums.EquipmentType;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_recipes_equipment")
public class RecipesEquipmentsEntity extends BaseEntityWithGeneratedId {

   @ManyToOne
   @JoinColumn(name = "recipe_id", referencedColumnName = "id")
   private RecipeAggregate recipe;

   @Enumerated(EnumType.STRING)
   private EquipmentType equipment;

   private Boolean mandatory;
}
