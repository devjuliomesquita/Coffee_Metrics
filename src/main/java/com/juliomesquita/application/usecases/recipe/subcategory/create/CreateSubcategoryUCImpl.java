package com.juliomesquita.application.usecases.recipe.subcategory.create;

import com.juliomesquita.application.usecases.recipe.subcategory.commom.SubcategoryResponse;
import com.juliomesquita.domain.entities.recipe.SubcategoryEntity;
import com.juliomesquita.domain.repositories.CategoryAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateSubcategoryUCImpl extends CreateSubcategoryUC {
    private final CategoryAggregateRepo repository;

    public CreateSubcategoryUCImpl(final CategoryAggregateRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public CreateSubcategoryOutput execute(final CreateSubcategoryInput input) {
        final var category = this.repository
            .findByIdOptional(input.categoryId())
            .orElseThrow(() -> new RuntimeException("id"));

        final var subcategory = SubcategoryEntity.create(input.description(), category.getId());

        category.addSubcategory(subcategory);
        this.repository.persist(category);

        return new CreateSubcategoryOutput(SubcategoryResponse.with(subcategory));
    }
}
