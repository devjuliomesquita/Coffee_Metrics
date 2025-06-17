package com.juliomesquita.domain.entities.recipe;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_notes")
public class NotesEntity extends PanacheEntity {

   private String description;

   @OneToOne(mappedBy = "notes")
   private RecipeAggregate recipe;
}
