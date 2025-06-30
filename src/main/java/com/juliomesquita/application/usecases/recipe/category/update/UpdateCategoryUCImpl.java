package com.juliomesquita.application.usecases.recipe.category.update;

import com.juliomesquita.application.usecases.recipe.category.commom.CategoryResponse;
import com.juliomesquita.domain.repositories.CategoryAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateCategoryUCImpl extends UpdateCategoryUC {
    private final CategoryAggregateRepo repository;

    public UpdateCategoryUCImpl(final CategoryAggregateRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public UpdateCategoryOutput execute(final UpdateCategoryInput input) {
        final var category = this.repository
            .findByIdOptional(input.id())
            .orElseThrow(() -> new RuntimeException("id"))
            .update(input.description());

        this.repository.persist(category);
        return new UpdateCategoryOutput(CategoryResponse.with(category));
    }
}
