package com.juliomesquita.application.usecases.coffee.retrives.findbyid;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.repositories.CoffeeAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class FindCoffeeByIdUCImpl extends FindCoffeeByIdUC {
    private final CoffeeAggregateRepo repository;

    public FindCoffeeByIdUCImpl(final CoffeeAggregateRepo repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public FindCoffeeByIdOutput execute(final Long coffeeId) {
        final var coffee = this.repository
            .findByIdOptional(coffeeId)
            .orElseThrow(() -> new RuntimeException("coffee id not found"));

        return new FindCoffeeByIdOutput(CoffeeResponse.with(coffee));
    }
}
