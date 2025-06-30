package com.juliomesquita.application.usecases.recipe.subcategory.update;

import com.juliomesquita.application.usecases.recipe.subcategory.commom.SubcategoryResponse;
import com.juliomesquita.domain.repositories.SubCategoryRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateSubcategoryUCImpl extends UpdateSubcategoryUC {
    private final SubCategoryRepo repository;

    public UpdateSubcategoryUCImpl(final SubCategoryRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public UpdateSubcategoryOutput execute(final UpdateSubcategoryInput input) {
        final var subcategory = this.repository
            .findByIdOptional(input.id())
            .orElseThrow(() -> new IllegalArgumentException("Subcategory id not found"))
            .update(input.description());

        this.repository.persist(subcategory);
        return new UpdateSubcategoryOutput(SubcategoryResponse.with(subcategory));
    }
}
