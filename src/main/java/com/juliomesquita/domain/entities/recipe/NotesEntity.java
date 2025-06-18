package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.entities.coffee.CoffeeAggregate;
import com.juliomesquita.domain.entities.creator.CreatorAggregate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_notes")
public class NotesEntity extends PanacheEntity {

   private String description;

   @OneToOne(mappedBy = "notes")
   @JoinColumn(name = "recipe_id", referencedColumnName = "id")
   private RecipeAggregate recipe;

   @OneToOne(mappedBy = "notes")
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;

   @OneToMany(mappedBy = "notes", fetch = FetchType.LAZY)
   private List<CoffeeAggregate> coffees;
}
