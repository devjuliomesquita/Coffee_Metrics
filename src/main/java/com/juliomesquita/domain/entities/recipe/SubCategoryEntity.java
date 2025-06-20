package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_subcategory")
public class SubCategoryEntity extends BaseEntityWithGeneratedId {

   @Column(name = "description", nullable = false)
   private String description;

   @ManyToOne
   @JoinColumn(name = "category_id", referencedColumnName = "id")
   private CategoryEntity category;

   @OneToMany(mappedBy = "subcategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private Set<TagEntity> tags;

   @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY)
   private Set<RecipeAggregate> recipes;

   public static SubCategoryEntity create(final String description, final Long categoryId) {
      final var category = CategoryEntity.getInstanceOnlyId(categoryId);
      final var tags = new HashSet<TagEntity>();
      final var recipes = new HashSet<RecipeAggregate>();

      return new SubCategoryEntity(description, category, tags, recipes);
   }

   public SubCategoryEntity update(final String description) {
      this.description = description;
      return this;
   }

   public SubCategoryEntity addTag(final TagEntity tag) {
      tag.bindToSubcategory(this);
      this.tags.add(tag);

      return this;
   }

   public static SubCategoryEntity getInstanceOnlyId(final Long id) {
      return new SubCategoryEntity(id);
   }

   private SubCategoryEntity(
       final String description,
       final CategoryEntity category,
       final Set<TagEntity> tags,
       final Set<RecipeAggregate> recipes
   ) {
      this.description = description;
      this.category = category;
      this.tags = tags;
      this.recipes = recipes;
   }

   private SubCategoryEntity(Long id) {
      this.id = id;
   }

   protected SubCategoryEntity() {
   }

   public String getDescription() {
      return description;
   }

   public CategoryEntity getCategory() {
      return category;
   }

   public Set<TagEntity> getTags() {
      return tags;
   }

   public Set<RecipeAggregate> getRecipes() {
      return recipes;
   }
}
