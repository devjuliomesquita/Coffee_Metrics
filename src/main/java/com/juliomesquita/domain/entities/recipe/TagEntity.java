package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tag")
public class TagEntity extends BaseEntityWithGeneratedId {

   private String description;

   @ManyToOne
   @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
   private SubCategoryEntity subCategory;
}
