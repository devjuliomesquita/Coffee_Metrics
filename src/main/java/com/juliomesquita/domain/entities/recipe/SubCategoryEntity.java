package com.juliomesquita.domain.entities.recipe;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_subcategory")
public class SubCategoryEntity extends PanacheEntity {

   private String description;

   @ManyToOne
   @JoinColumn(name = "category_id", referencedColumnName = "id")
   private CategoryEntity category;

   @OneToMany(mappedBy = "subcategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private Set<TagEntity> tags;

   @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY)
   private List<RecipeAggregate> recipes;
}
