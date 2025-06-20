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

   public static RecipesEquipmentsEntity create(final EquipmentType equipment, final Boolean mandatory) {
      return new RecipesEquipmentsEntity(null, equipment, mandatory);
   }

   public RecipesEquipmentsEntity update(final EquipmentType equipment, final Boolean mandatory) {
      this.equipment = equipment;
      this.mandatory = mandatory;
      return this;
   }

   public RecipesEquipmentsEntity bindToRecipe(final RecipeAggregate recipe) {
      this.recipe = recipe;
      return this;
   }


   private RecipesEquipmentsEntity(
       final RecipeAggregate recipe,
       final EquipmentType equipment,
       final Boolean mandatory
   ) {
      this.recipe = recipe;
      this.equipment = equipment;
      this.mandatory = mandatory;
   }

   protected RecipesEquipmentsEntity() {
   }

   public RecipeAggregate getRecipe() {
      return recipe;
   }

   public EquipmentType getEquipment() {
      return equipment;
   }

   public Boolean getMandatory() {
      return mandatory;
   }
}
