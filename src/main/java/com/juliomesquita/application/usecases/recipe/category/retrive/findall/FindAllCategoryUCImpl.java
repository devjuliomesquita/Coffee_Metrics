package com.juliomesquita.application.usecases.recipe.category.retrive.findall;

import com.juliomesquita.application.usecases.recipe.category.commom.CategoryResponse;
import com.juliomesquita.domain.commom.Pagination;
import com.juliomesquita.domain.commom.SearchQuery;
import com.juliomesquita.domain.commom.SearchQueryUtils;
import com.juliomesquita.domain.entities.recipe.CategoryEntity;
import com.juliomesquita.domain.repositories.CategoryAggregateRepo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FindAllCategoryUCImpl extends FindAllCategoryUC {
    private final CategoryAggregateRepo repository;

    public FindAllCategoryUCImpl(final CategoryAggregateRepo repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<CategoryResponse> execute(final SearchQuery aCommand) {
        final var params = SearchQueryUtils.buildParams(aCommand);
        final StringBuilder query = SearchQueryUtils.buildQuery(params);
        final Sort sort = SearchQueryUtils.buildSort(aCommand);
        final Page page = SearchQueryUtils.buildPage(aCommand);

        final PanacheQuery<CategoryEntity> categoryQuery = this.repository
            .find(query.toString(), sort, params.params())
            .page(page);

        return Pagination.create(
            categoryQuery.list().stream().map(CategoryResponse::with).toList(),
            page.index + 1,
            page.size,
            categoryQuery.list().size()
        );
    }
}
