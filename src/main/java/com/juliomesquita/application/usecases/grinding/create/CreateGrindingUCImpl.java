package com.juliomesquita.application.usecases.grinding.create;

import com.juliomesquita.application.usecases.grinding.commom.GrindingResponse;
import com.juliomesquita.domain.entities.grinding.GrindingAggregate;
import com.juliomesquita.domain.repositories.GrindingAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateGrindingUCImpl extends CreateGrindingUC {
    private final GrindingAggregateRepo repository;

    public CreateGrindingUCImpl(final GrindingAggregateRepo repository) {
        this.repository = repository;
    }

    @Override
    public CreateGrindingOutput execute(final CreateGrindingInput input) {
        final var grinding = GrindingAggregate.create(input.brand(), input.model(), input.clicks());
        this.repository.persist(grinding);
        return new CreateGrindingOutput(GrindingResponse.with(grinding));
    }
}
