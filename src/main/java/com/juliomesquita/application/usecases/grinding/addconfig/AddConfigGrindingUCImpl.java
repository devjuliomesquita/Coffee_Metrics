package com.juliomesquita.application.usecases.grinding.addconfig;

import com.juliomesquita.application.usecases.grinding.commom.GrindingResponse;
import com.juliomesquita.domain.entities.grinding.GrindingConfigEntity;
import com.juliomesquita.domain.repositories.GrindingAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AddConfigGrindingUCImpl extends AddConfigGrindingUC {
    private final GrindingAggregateRepo repository;

    public AddConfigGrindingUCImpl(final GrindingAggregateRepo repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public AddConfigGrindingOutput execute(final AddConfigGrindingInput input) {
        final var grinding = this.repository.findByIdOptional(input.grindingId())
            .orElseThrow(() -> new RuntimeException("No such grinding id: " + input.grindingId()));

        final var config = GrindingConfigEntity
            .create(input.clicks(), grinding.getId());

        input.intervals().forEach(i -> {
            config.addIntervals(i.categoryName(), i.interval());
        });

        grinding.addConfig(config);
        this.repository.persist(grinding);

        return new AddConfigGrindingOutput(GrindingResponse.with(grinding));
    }
}
