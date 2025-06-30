package com.juliomesquita.application.usecases.recipe.subcategory.delete;

import com.juliomesquita.application.usecases.recipe.subcategory.commom.SubcategoryResponse;
import com.juliomesquita.domain.repositories.SubCategoryRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteSubcategoryUCImpl extends DeleteSubcategoryUC {
    private final SubCategoryRepo repository;

    public DeleteSubcategoryUCImpl(final SubCategoryRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public SubcategoryResponse execute(final Long id) {
        final var subcategory = this.repository
            .findByIdOptional(id)
            .orElseThrow(() -> new IllegalArgumentException("Subcategory id not found"));

        final var response = SubcategoryResponse.with(subcategory);
        this.repository.delete(subcategory);
        return response;
    }
}
