package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_tag")
public class TagEntity extends BaseEntityWithGeneratedId {

   @Column(name = "description", nullable = false)
   private String description;

   @ManyToOne
   @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
   private SubCategoryEntity subCategory;

   public static TagEntity create(final String description){
      return new TagEntity(description, null);
   }

   public TagEntity update(final String description){
      this.description = description;
      return this;
   }

   public TagEntity bindToSubcategory(final SubCategoryEntity subCategory){
      this.subCategory = subCategory;
      return this;
   }

   public TagEntity(final String description, final SubCategoryEntity subCategory) {
      this.description = description;
      this.subCategory = subCategory;
   }

   public TagEntity() {
   }

   public String getDescription() {
      return description;
   }

   public SubCategoryEntity getSubCategory() {
      return subCategory;
   }
}
