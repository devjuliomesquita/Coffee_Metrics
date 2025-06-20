package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_tag")
public class TagEntity extends BaseEntityWithGeneratedId {

   @Column(name = "description", nullable = false)
   private String description;

   @ManyToOne
   @JoinColumn(name = "subcategory_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tag_subcategory"))
   private SubcategoryEntity subcategory;

   public static TagEntity create(final String description){
      return new TagEntity(description, null);
   }

   public TagEntity update(final String description){
      this.description = description;
      return this;
   }

   public TagEntity bindToSubcategory(final SubcategoryEntity subCategory){
      this.subcategory = subCategory;
      return this;
   }

   public TagEntity(final String description, final SubcategoryEntity subcategory) {
      this.description = description;
      this.subcategory = subcategory;
   }

   public TagEntity() {
   }

   public String getDescription() {
      return description;
   }

   public SubcategoryEntity getSubcategory() {
      return subcategory;
   }
}
