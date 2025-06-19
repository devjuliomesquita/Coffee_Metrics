package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.coffee.CoffeeAggregate;
import com.juliomesquita.domain.entities.creator.CreatorAggregate;
import com.juliomesquita.domain.entities.grinding.GrindingAggregate;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_notes")
public class NotesEntity extends BaseEntityWithGeneratedId {

   @Column(name = "description", nullable = false)
   private String description;

   @OneToOne(mappedBy = "notes")
   @JoinColumn(name = "recipe_id", referencedColumnName = "id")
   private RecipeAggregate recipe;

   @OneToOne(mappedBy = "notes")
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;

   @OneToMany(mappedBy = "notes", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   private List<CoffeeAggregate> coffees;

   @OneToMany(mappedBy = "notes", fetch = FetchType.LAZY)
   private List<GrindingAggregate> grindings;

   public static NotesEntity create(
       final String description, final CreatorAggregate creator
   ) {
      final var coffees = new ArrayList<CoffeeAggregate>();
      final var grindings = new ArrayList<GrindingAggregate>();
      return new NotesEntity(description, null, creator, coffees, grindings);
   }

   public NotesEntity update(final String description) {
      this.description = description;
      return this;
   }

   public NotesEntity bindToRecipe(final RecipeAggregate recipe) {
      this.recipe = recipe;
      return this;
   }

   public NotesEntity addCoffee(final CoffeeAggregate coffee) {
      final boolean contains = this.coffees.contains(coffee);
      if (!contains) {
         coffee.bindToNotes(this);
         this.coffees.add(coffee);
      }
      return this;
   }

   private NotesEntity(
       final String description,
       final RecipeAggregate recipe,
       final CreatorAggregate creator,
       final List<CoffeeAggregate> coffees,
       final List<GrindingAggregate> grindings
   ) {
      this.description = description;
      this.recipe = recipe;
      this.creator = creator;
      this.coffees = coffees;
      this.grindings = grindings;
   }

   protected NotesEntity() {
   }

   public String getDescription() {
      return description;
   }

   public RecipeAggregate getRecipe() {
      return recipe;
   }

   public CreatorAggregate getCreator() {
      return creator;
   }

   public List<CoffeeAggregate> getCoffees() {
      return coffees;
   }

   public List<GrindingAggregate> getGrindings() {
      return grindings;
   }
}
