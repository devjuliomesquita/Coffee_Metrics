package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.creator.CreatorFavoritesEntity;
import com.juliomesquita.domain.entities.creator.CreatorPostsEntity;
import com.juliomesquita.domain.enums.GrindingType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_recipes")
public class RecipeAggregate extends BaseEntityWithGeneratedId {

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "grams_of_coffee", nullable = false)
   private Integer gramsOfCoffee;

   @Column(name = "proportion", nullable = false)
   private String proportion;

   @Enumerated(EnumType.STRING)
   @Column(name = "grinding", nullable = false)
   private GrindingType grinding;

   @Column(name = "extraction_time", nullable = false)
   private Integer extractionTime;

   @Column(name = "grams_of_water", nullable = false)
   private Integer gramsOfWater;

   @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<RecipeStepsEntity> steps;

   @ManyToOne
   @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
   private SubCategoryEntity subCategory;

   @OneToOne(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private NotesEntity notes;

   @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
   private Set<CreatorFavoritesEntity> favorites;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "post_id", referencedColumnName = "id")
   private CreatorPostsEntity post;

   @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
   private Set<RecipesEquipmentsEntity> equipments;


   public static RecipeAggregate create(
       final String description,
       final Integer gramsOfCoffee,
       final GrindingType grinding,
       final Integer extractionTime,
       final Integer gramsOfWater,
       final Long subcategoryId
   ) {
      final var steps = new HashSet<RecipeStepsEntity>();
      final var favorites = new HashSet<CreatorFavoritesEntity>();
      final var equipments = new HashSet<RecipesEquipmentsEntity>();
      final var subCategoryEntity = SubCategoryEntity.getInstanceOnlyId(subcategoryId);
      final var proportion = calculateProportion(gramsOfCoffee, gramsOfWater);

      return new RecipeAggregate(
          description, gramsOfCoffee, proportion, grinding, extractionTime, gramsOfWater, steps, subCategoryEntity,
          null, favorites, null, equipments
      );
   }

   public RecipeAggregate update(
       final String description,
       final Integer gramsOfCoffee,
       final GrindingType grinding,
       final Integer extractionTime,
       final Integer gramsOfWater,
       final Long subcategoryId
   ) {
      this.description = description;
      this.gramsOfCoffee = gramsOfCoffee;
      this.grinding = grinding;
      this.extractionTime = extractionTime;
      this.gramsOfWater = gramsOfWater;
      this.proportion = calculateProportion(gramsOfCoffee, gramsOfWater);
      this.subCategory = SubCategoryEntity.getInstanceOnlyId(subcategoryId);

      return this;
   }

   public RecipeAggregate addSteps(final Set<RecipeStepsEntity> steps) {
      steps.forEach(this::addStep);
      return this;
   }

   public void addStep(final RecipeStepsEntity step) {
      final boolean contains = this.steps.contains(step);
      if (!contains) {
         step.bindToRecipe(this);
         this.steps.add(step);
         return;
      }
      this.steps.remove(step);
      step.bindToRecipe(this);
      this.steps.add(step);
   }

   public RecipeAggregate addNotes(final NotesEntity notes) {
      notes.bindToRecipe(this);
      this.notes = notes;
      return this;
   }

   private static String calculateProportion(final int gramsOfCoffee, final int gramsOfWater) {
      if (gramsOfCoffee <= 0) {
         throw new IllegalArgumentException("gramsOfCoffee must be > 0");
      }

      double ratio = (double) gramsOfWater / gramsOfCoffee;
      int roundedRatio = (int) Math.round(ratio);

      return "1:" + roundedRatio;
   }

   public static RecipeAggregate getInstanceOnlyId(final Long id){
      return new RecipeAggregate(id);
   }

   private RecipeAggregate(
       final String description,
       final Integer gramsOfCoffee,
       final String proportion,
       final GrindingType grinding,
       final Integer extractionTime,
       final Integer gramsOfWater,
       final Set<RecipeStepsEntity> steps,
       final SubCategoryEntity subCategory,
       final NotesEntity notes,
       final Set<CreatorFavoritesEntity> favorites,
       final CreatorPostsEntity post,
       final Set<RecipesEquipmentsEntity> equipments
   ) {
      this.description = description;
      this.gramsOfCoffee = gramsOfCoffee;
      this.proportion = proportion;
      this.grinding = grinding;
      this.extractionTime = extractionTime;
      this.gramsOfWater = gramsOfWater;
      this.steps = steps;
      this.subCategory = subCategory;
      this.notes = notes;
      this.favorites = favorites;
      this.post = post;
      this.equipments = equipments;
   }

   private RecipeAggregate(final Long id) {
      this.id = id;
   }

   protected RecipeAggregate() {
   }

   public String getDescription() {
      return description;
   }

   public Integer getGramsOfCoffee() {
      return gramsOfCoffee;
   }

   public String getProportion() {
      return proportion;
   }

   public GrindingType getGrinding() {
      return grinding;
   }

   public Integer getExtractionTime() {
      return extractionTime;
   }

   public Integer getGramsOfWater() {
      return gramsOfWater;
   }

   public Set<RecipeStepsEntity> getSteps() {
      return steps;
   }

   public SubCategoryEntity getSubCategory() {
      return subCategory;
   }

   public NotesEntity getNotes() {
      return notes;
   }

   public Set<CreatorFavoritesEntity> getFavorites() {
      return favorites;
   }

   public CreatorPostsEntity getPost() {
      return post;
   }

   public Set<RecipesEquipmentsEntity> getEquipments() {
      return equipments;
   }
}
