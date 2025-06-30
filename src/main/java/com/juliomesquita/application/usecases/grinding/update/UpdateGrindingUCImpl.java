package com.juliomesquita.application.usecases.grinding.update;

import com.juliomesquita.application.usecases.grinding.commom.GrindingResponse;
import com.juliomesquita.domain.repositories.GrindingAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateGrindingUCImpl extends UpdateGrindingUC {
    private final GrindingAggregateRepo repository;

    public UpdateGrindingUCImpl(final GrindingAggregateRepo repository) {
        this.repository = repository;
    }

    @Override
    public UpdateGrindingOutput execute(final UpdateGrindingInput input) {
        final var grinding = this.repository.findByIdOptional(input.id())
            .orElseThrow(() -> new RuntimeException("No such grinding id: " + input.id()))
            .update(input.brand(), input.model(), input.clicks());

        this.repository.persist(grinding);
        return new UpdateGrindingOutput(GrindingResponse.with(grinding));
    }
}
