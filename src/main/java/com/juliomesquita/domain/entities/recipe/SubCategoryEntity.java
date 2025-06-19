package com.juliomesquita.domain.entities.recipe;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_subcategory")
public class SubCategoryEntity extends BaseEntityWithGeneratedId {

   private String description;

   @ManyToOne
   @JoinColumn(name = "category_id", referencedColumnName = "id")
   private CategoryEntity category;

   @OneToMany(mappedBy = "subcategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private Set<TagEntity> tags;

   @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY)
   private Set<RecipeAggregate> recipes;
}
