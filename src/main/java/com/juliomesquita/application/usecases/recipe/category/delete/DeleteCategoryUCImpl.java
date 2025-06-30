package com.juliomesquita.application.usecases.recipe.category.delete;

import com.juliomesquita.application.usecases.recipe.category.commom.CategoryResponse;
import com.juliomesquita.domain.repositories.CategoryAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteCategoryUCImpl extends DeleteCategoryUC {
    private final CategoryAggregateRepo repository;

    public DeleteCategoryUCImpl(final CategoryAggregateRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public CategoryResponse execute(final Long input) {
        final var category = this.repository
            .findByIdOptional(input)
            .orElseThrow(() -> new RuntimeException("id"));

        CategoryResponse response = CategoryResponse.with(category);

        this.repository.delete(category);
        return response;
    }
}
