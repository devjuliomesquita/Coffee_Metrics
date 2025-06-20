package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class CategoryEntity extends BaseEntityWithGeneratedId {

   @Column(name = "description", nullable = false)
   private String description;

   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<SubcategoryEntity> subCategories;

   public static CategoryEntity create(final String description) {
      final var subcategory = new HashSet<SubcategoryEntity>();
      return new CategoryEntity(description, subcategory);
   }

   public CategoryEntity update(final String description) {
      this.description = description;
      return this;
   }

   public static CategoryEntity getInstanceOnlyId(final Long id){
      return new CategoryEntity(id);
   }

   private CategoryEntity(final String description, final Set<SubcategoryEntity> subCategories) {
      this.description = description;
      this.subCategories = subCategories;
   }

   private CategoryEntity(final Long id) {
      this.id = id;
   }

   protected CategoryEntity() {
   }

   public String getDescription() {
      return description;
   }

   public Set<SubcategoryEntity> getSubCategories() {
      return subCategories;
   }
}
