package com.juliomesquita.application.usecases.recipe.category.create;

import com.juliomesquita.application.usecases.recipe.category.commom.CategoryResponse;
import com.juliomesquita.domain.repositories.CategoryAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateCategoryUCImpl extends CreateCategoryUC {
    private final CategoryAggregateRepo repository;

    public CreateCategoryUCImpl(final CategoryAggregateRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public CreateCategoryOutput execute(final CreateCategoryInput input) {
        final var category = com.juliomesquita.domain.entities.recipe.CategoryEntity.create(input.description());

        this.repository.persist(category);
        return new CreateCategoryOutput(CategoryResponse.with(category));
    }
}
