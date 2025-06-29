package com.juliomesquita.application.usecases.coffee.retrives.findall;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.commom.Pagination;
import com.juliomesquita.domain.commom.SearchQuery;
import com.juliomesquita.domain.commom.SearchQueryUtils;
import com.juliomesquita.domain.entities.coffee.CoffeeAggregate;
import com.juliomesquita.domain.repositories.CoffeeAggregateRepo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class FindAllCoffeeUCImpl extends FindAllCoffeeUC {
    private final CoffeeAggregateRepo repository;

    public FindAllCoffeeUCImpl(final CoffeeAggregateRepo repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Pagination<CoffeeResponse> execute(final SearchQuery aCommand) {
        final var params = SearchQueryUtils.buildParams(aCommand);
        final StringBuilder query = SearchQueryUtils.buildQuery(params);
        final Sort sort = SearchQueryUtils.buildSort(aCommand);
        final Page page = SearchQueryUtils.buildPage(aCommand);

        final PanacheQuery<CoffeeAggregate> coffeeQuery = this.repository
            .find(query.toString(), sort, params.params())
            .page(page);
        System.out.println("coffeeQuery = " + coffeeQuery.list());
        System.out.println("coffeeQuery = " + coffeeQuery.count());
        System.out.println("coffeeQuery = " + coffeeQuery.list().size());
        return Pagination.create(
            coffeeQuery.list().stream().map(CoffeeResponse::with).toList(),
            page.index + 1,
            page.size,
            coffeeQuery.list().size()
        );
    }
}
