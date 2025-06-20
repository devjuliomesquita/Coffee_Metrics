package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.commom.BaseEntityWithManualId;
import com.juliomesquita.domain.entities.grinding.GrindingAggregate;
import com.juliomesquita.domain.entities.recipe.RecipeAggregate;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_creator_favorites")
public class CreatorFavoritesEntity extends BaseEntityWithManualId {

   @MapsId
   @OneToOne
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;

   @ManyToMany
   @JoinTable(
       name = "tb_creator_favorites_recipes",
       joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
       inverseJoinColumns = @JoinColumn(name = "creator_favorite_id", referencedColumnName = "id")
   )
   private Set<RecipeAggregate> recipes;

   @OneToOne(mappedBy = "favorites", fetch = FetchType.LAZY)
   private UnitsSystemEntity unitsSystem;

   @ManyToOne
   @JoinColumn(name = "grinding_id", referencedColumnName = "id")
   private GrindingAggregate grinding;

   public static CreatorFavoritesEntity create(final Long creatorId) {
      final var creator = CreatorAggregate.getInstanceOnlyId(creatorId);
      final var recipes = new HashSet<RecipeAggregate>();

      return new CreatorFavoritesEntity(creator, recipes, null, null);
   }

   public CreatorFavoritesEntity addUnitsSystem(
       final String weight, final String temperature, final String measures, final String proportion
   ) {
      this.unitsSystem = UnitsSystemEntity.create(weight, temperature, measures, proportion).bindToFavorites(this);
      return this;
   }

   public CreatorFavoritesEntity updateUnitsSystem(
       final String weight, final String temperature, final String measures, final String proportion
   ) {
      this.unitsSystem.update(weight, temperature, measures, proportion);
      return this;
   }

   public CreatorFavoritesEntity addGrinding(final Long grindingId) {
      this.grinding = GrindingAggregate.getInstanceOnlyId(grindingId);
      return this;
   }

   public CreatorFavoritesEntity addRecipes(final Long recipesId) {
      final var recipe = com.juliomesquita.domain.entities.recipe.RecipeAggregate.getInstanceOnlyId(recipesId);
      this.recipes.add(recipe);

      return this;
   }

   private CreatorFavoritesEntity(
       final CreatorAggregate creator,
       final Set<RecipeAggregate> recipes,
       final UnitsSystemEntity unitsSystem,
       final GrindingAggregate grinding
   ) {
      this.creator = creator;
      this.recipes = recipes;
      this.unitsSystem = unitsSystem;
      this.grinding = grinding;
   }

   protected CreatorFavoritesEntity() {
   }

   public CreatorAggregate getCreator() {
      return creator;
   }

   public Set<RecipeAggregate> getRecipes() {
      return recipes;
   }

   public UnitsSystemEntity getUnitsSystem() {
      return unitsSystem;
   }

   public GrindingAggregate getGrinding() {
      return grinding;
   }
}
