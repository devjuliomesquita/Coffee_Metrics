package com.juliomesquita.application.usecases.recipe.subcategory.commom;

import com.juliomesquita.domain.entities.recipe.TagEntity;

public record TagResponse(Long id, String description) {
    public static TagResponse with(final TagEntity entity) {
        return new TagResponse(entity.getId(), entity.getDescription());
    }
}
