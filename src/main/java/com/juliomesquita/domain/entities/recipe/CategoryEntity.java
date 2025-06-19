package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_category")
public class CategoryEntity extends BaseEntityWithGeneratedId {

   private String description;

   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<SubCategoryEntity> subCategories;
}
