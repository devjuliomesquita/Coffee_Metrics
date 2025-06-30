package com.juliomesquita.domain.repositories;

import com.juliomesquita.domain.entities.recipe.SubcategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubCategoryRepo implements PanacheRepository<SubcategoryEntity> {
}
