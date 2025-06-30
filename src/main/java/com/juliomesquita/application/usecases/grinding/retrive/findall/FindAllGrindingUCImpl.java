package com.juliomesquita.application.usecases.grinding.retrive.findall;

import com.juliomesquita.application.usecases.grinding.commom.GrindingResponse;
import com.juliomesquita.domain.commom.Pagination;
import com.juliomesquita.domain.commom.SearchQuery;
import com.juliomesquita.domain.commom.SearchQueryUtils;
import com.juliomesquita.domain.entities.grinding.GrindingAggregate;
import com.juliomesquita.domain.repositories.GrindingAggregateRepo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FindAllGrindingUCImpl extends FindAllGrindingUC {
    private final GrindingAggregateRepo repository;

    public FindAllGrindingUCImpl(final GrindingAggregateRepo repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<GrindingResponse> execute(final SearchQuery aCommand) {
        final var params = SearchQueryUtils.buildParams(aCommand);
        final StringBuilder query = SearchQueryUtils.buildQuery(params);
        final Sort sort = SearchQueryUtils.buildSort(aCommand);
        final Page page = SearchQueryUtils.buildPage(aCommand);

        final PanacheQuery<GrindingAggregate> grindingQuery = this.repository
            .find(query.toString(), sort, params.params())
            .page(page);

        return Pagination.create(
            grindingQuery.list().stream().map(GrindingResponse::with).toList(),
            page.index + 1,
            page.size,
            grindingQuery.list().size()
        );
    }
}
