package com.juliomesquita.application.usecases.coffee.update;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.repositories.CoffeeAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class UpdateCoffeeUCImpl extends UpdateCoffeeUC {
    private final CoffeeAggregateRepo repository;

    public UpdateCoffeeUCImpl(final CoffeeAggregateRepo repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional
    @Override
    public UpdateCoffeeOutput execute(final UpdateCoffeeInput aCommand) {
        final var coffee = this.repository
                .findByIdOptional(aCommand.coffeeId())
                .orElseThrow(() -> new RuntimeException("coffee id not found"))
                .update(aCommand.infoCoffee().name(), aCommand.infoCoffee().species(), aCommand.infoCoffee().sizeType(),
                        aCommand.infoCoffee().roastingLevel(), aCommand.infoCoffee().roastingDate(),
                        aCommand.infoCoffee().points(), aCommand.infoCoffee().sensory());

        this.repository.persist(coffee);
        return new UpdateCoffeeOutput(CoffeeResponse.with(coffee));
    }
}
