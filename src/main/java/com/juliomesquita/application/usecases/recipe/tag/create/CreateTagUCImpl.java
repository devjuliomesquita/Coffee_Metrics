package com.juliomesquita.application.usecases.recipe.tag.create;

import com.juliomesquita.application.usecases.recipe.subcategory.commom.TagResponse;
import com.juliomesquita.domain.entities.recipe.TagEntity;
import com.juliomesquita.domain.repositories.SubCategoryRepo;
import jakarta.transaction.Transactional;

public class CreateTagUCImpl extends CreateTagUC {
    private final SubCategoryRepo repository;

    public CreateTagUCImpl(final SubCategoryRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public CreateTagOutput execute(final CreateTagInput input) {
        final var tag = TagEntity.create(input.description());

        final var subcategory = this.repository
            .findByIdOptional(input.subcategoryId())
            .orElseThrow(() -> new RuntimeException("Subcategory not found"))
            .addTag(tag);

        this.repository.persist(subcategory);
        return new CreateTagOutput(TagResponse.with(tag));
    }
}
