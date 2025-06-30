package com.juliomesquita.application.usecases.recipe.category.retrive.findbyid;

import com.juliomesquita.application.usecases.recipe.category.commom.CategoryResponse;
import com.juliomesquita.domain.repositories.CategoryAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FindCategoryByIdUCImpl extends FindCategoryByIdUC {
    private final CategoryAggregateRepo repository;

    public FindCategoryByIdUCImpl(final CategoryAggregateRepo repository) {
        this.repository = repository;
    }

    @Override
    public CategoryResponse execute(final Long input) {
        return this.repository
            .findByIdOptional(input)
            .map(CategoryResponse::with)
            .orElseThrow(() -> new RuntimeException("id"));
    }
}
