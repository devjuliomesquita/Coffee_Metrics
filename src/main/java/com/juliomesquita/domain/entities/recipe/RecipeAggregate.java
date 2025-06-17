package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.entities.creator.CreatorFavorites;
import com.juliomesquita.domain.enums.GrindingType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_recipes")
public class RecipeAggregate extends PanacheEntity {

   private String description;

   private Integer gramsOfCoffee;

   private String proportion;

   @Enumerated(EnumType.STRING)
   private GrindingType grinding;

   private Integer extractionTime;

   private Integer gramsOfWater;

   @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<RecipeStepsEntity> steps;

   @ManyToOne
   @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
   private SubCategoryEntity subCategory;

   @OneToOne(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private NotesEntity notes;

   private OffsetDateTime createdAt;

   private OffsetDateTime updatedAt;

   @ManyToMany(mappedBy = "recipes")
   private Set<CreatorFavorites> favorites;

}
