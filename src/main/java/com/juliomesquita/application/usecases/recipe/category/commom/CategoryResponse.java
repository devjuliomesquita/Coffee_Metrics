package com.juliomesquita.application.usecases.recipe.category.commom;

import com.juliomesquita.application.usecases.recipe.subcategory.commom.SubcategoryResponse;
import com.juliomesquita.domain.entities.recipe.CategoryEntity;

import java.util.Set;
import java.util.stream.Collectors;

public record CategoryResponse(
    Long id,
    String description,
    Set<SubcategoryResponse> subcategories
) {
    public static CategoryResponse with(final CategoryEntity entity) {
        return new CategoryResponse(
            entity.getId(), entity.getDescription(),
            entity.getSubCategories().stream().map(SubcategoryResponse::with).collect(Collectors.toSet())
        );
    }
}
