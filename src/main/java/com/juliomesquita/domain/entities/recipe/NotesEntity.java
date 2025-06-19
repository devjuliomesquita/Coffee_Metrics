package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.coffee.CoffeeAggregate;
import com.juliomesquita.domain.entities.creator.CreatorAggregate;
import com.juliomesquita.domain.entities.grinding.GrindingAggregate;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_notes")
public class NotesEntity extends BaseEntityWithGeneratedId {

   private String description;

   @OneToOne(mappedBy = "notes")
   @JoinColumn(name = "recipe_id", referencedColumnName = "id")
   private RecipeAggregate recipe;

   @OneToOne(mappedBy = "notes")
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;

   @OneToMany(mappedBy = "notes", fetch = FetchType.LAZY)
   private List<CoffeeAggregate> coffees;

   @OneToMany(mappedBy = "notes", fetch = FetchType.LAZY)
   private List<GrindingAggregate> grindings;
}
