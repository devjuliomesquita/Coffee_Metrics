package com.juliomesquita.application.usecases.recipe.category.retrive.findall;

import com.juliomesquita.application.usecases.recipe.category.commom.CategoryResponse;
import com.juliomesquita.domain.commom.Pagination;
import com.juliomesquita.domain.commom.SearchQuery;
import com.juliomesquita.domain.commom.UseCase;

public abstract class FindAllCategoryUC extends UseCase<SearchQuery, Pagination<CategoryResponse>> {
}
