package com.juliomesquita.application.usecases.grinding.delete;

import com.juliomesquita.application.usecases.grinding.commom.GrindingResponse;
import com.juliomesquita.domain.repositories.GrindingAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteGrindingUCImpl extends DeleteGrindingUC {
    private final GrindingAggregateRepo repository;

    public DeleteGrindingUCImpl(final GrindingAggregateRepo repository) {
        this.repository = repository;
    }

    @Override
    public DeleteGrindingOutput execute(final Long input) {
        final var grinding = this.repository
            .findByIdOptional(input)
            .orElseThrow(() -> new RuntimeException("grinding id not found"));

        final var response = GrindingResponse.with(grinding);

        this.repository.deleteById(grinding.getId());
        return new DeleteGrindingOutput(response);
    }
}
