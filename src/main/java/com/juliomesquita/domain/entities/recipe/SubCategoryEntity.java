package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

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

   public static SubCategoryEntity getInstanceOnlyId(final Long id) {
      return new SubCategoryEntity(id);
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
