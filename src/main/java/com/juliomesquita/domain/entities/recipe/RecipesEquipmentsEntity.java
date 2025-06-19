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
   @Column(name = "equipment", nullable = false)
   private EquipmentType equipment;

   @Column(name = "mandatory", nullable = false)
   private Boolean mandatory;
}
