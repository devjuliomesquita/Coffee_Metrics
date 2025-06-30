package com.juliomesquita.domain.repositories;

import com.juliomesquita.domain.entities.recipe.CategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryAggregateRepo implements PanacheRepository<CategoryEntity> {
}
