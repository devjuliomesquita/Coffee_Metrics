package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_recipe_steps")
public class RecipeStepsEntity extends BaseEntityWithGeneratedId {

   @Column(name = "execution_order", nullable = false)
   private Integer executionOrder;

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "duration_time")
   private Integer durationTime;

   @ManyToOne
   @JoinColumn(name = "recipe_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_steps_recipe"))
   private RecipeAggregate recipe;

   public static RecipeStepsEntity create(final Integer executionOrder, final String description, final Integer durationTime) {
      return new RecipeStepsEntity(executionOrder, description, durationTime, null);
   }

   public RecipeStepsEntity bindToRecipe(final RecipeAggregate recipe){
      this.recipe = recipe;
      return this;
   }

   private RecipeStepsEntity(
       final Integer executionOrder,
       final String description,
       final Integer durationTime,
       final RecipeAggregate recipe
   ) {
      this.executionOrder = executionOrder;
      this.description = description;
      this.durationTime = durationTime;
      this.recipe = recipe;
   }

   protected RecipeStepsEntity() {
   }

   public Integer getExecutionOrder() {
      return executionOrder;
   }

   public String getDescription() {
      return description;
   }

   public Integer getDurationTime() {
      return durationTime;
   }

   public RecipeAggregate getRecipe() {
      return recipe;
   }
}
