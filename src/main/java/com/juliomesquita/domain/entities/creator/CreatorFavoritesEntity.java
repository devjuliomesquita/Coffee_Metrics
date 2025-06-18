package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.entities.recipe.RecipeAggregate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_creator_favorites")
public class CreatorFavoritesEntity extends PanacheEntity {

   @MapsId
   @OneToOne(mappedBy = "favorites")
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
}
