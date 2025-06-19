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
}
