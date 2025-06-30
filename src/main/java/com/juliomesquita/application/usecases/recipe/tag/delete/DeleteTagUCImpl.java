package com.juliomesquita.application.usecases.recipe.tag.delete;

import com.juliomesquita.application.usecases.recipe.subcategory.commom.TagResponse;
import com.juliomesquita.domain.repositories.TagRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteTagUCImpl extends DeleteTagUC {
    private final TagRepo repository;

    public DeleteTagUCImpl(final TagRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public TagResponse execute(final Long id) {
        final var tag = this.repository
            .findByIdOptional(id)
            .orElseThrow(() -> new IllegalArgumentException("Subcategory id not found"));

        final var response = TagResponse.with(tag);
        this.repository.delete(tag);
        return response;
    }
}
