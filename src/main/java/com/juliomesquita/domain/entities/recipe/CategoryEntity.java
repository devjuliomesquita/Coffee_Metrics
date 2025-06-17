package com.juliomesquita.domain.entities.recipe;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_category")
public class CategoryEntity extends PanacheEntity {

   private String description;

   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<SubCategoryEntity> subCategories;
}
