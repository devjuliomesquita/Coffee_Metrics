package com.juliomesquita.application.usecases.grinding.retrive.findbyid;

import com.juliomesquita.application.usecases.grinding.commom.GrindingResponse;
import com.juliomesquita.domain.repositories.GrindingAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FindGrindingByIdUCImpl extends FindGrindingByIdUC {
    private final GrindingAggregateRepo repository;

    public FindGrindingByIdUCImpl(final GrindingAggregateRepo repository) {
        this.repository = repository;
    }

    @Override
    public GrindingResponse execute(final Long input) {
        final var grinding = this.repository.findByIdOptional(input)
            .orElseThrow(() -> new RuntimeException("No such grinding id: " + input));

        return GrindingResponse.with(grinding);
    }
}
