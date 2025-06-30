package com.juliomesquita.application.usecases.recipe.tag.update;

import com.juliomesquita.application.usecases.recipe.subcategory.commom.TagResponse;
import com.juliomesquita.domain.repositories.TagRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UpdateTagUCImpl extends UpdateTagUC {
    private final TagRepo repository;

    public UpdateTagUCImpl(final TagRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public UpdateTagOutput execute(final UpdateTagInput input) {
        final var tag = this.repository
            .findByIdOptional(input.id())
            .orElseThrow(() -> new IllegalArgumentException("Subcategory id not found"))
            .update(input.description());

        this.repository.persist(tag);
        return new UpdateTagOutput(TagResponse.with(tag));
    }
}
