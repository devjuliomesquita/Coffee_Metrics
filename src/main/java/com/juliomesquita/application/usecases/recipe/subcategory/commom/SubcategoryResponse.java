package com.juliomesquita.application.usecases.recipe.subcategory.commom;

import com.juliomesquita.domain.entities.recipe.SubcategoryEntity;

import java.util.Set;
import java.util.stream.Collectors;

public record SubcategoryResponse(
    Long id,
    String description,
    Set<TagResponse> tags
) {
    public static SubcategoryResponse with(final SubcategoryEntity entity) {
        return new SubcategoryResponse(
            entity.getId(), entity.getDescription(), entity.getTags().stream().map(TagResponse::with).collect(Collectors.toSet())
        );
    }
}
