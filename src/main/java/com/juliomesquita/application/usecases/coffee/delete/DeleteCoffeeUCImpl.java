package com.juliomesquita.application.usecases.coffee.delete;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.repositories.CoffeeAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class DeleteCoffeeUCImpl extends DeleteCoffeeUC {
    private final CoffeeAggregateRepo repository;

    public DeleteCoffeeUCImpl(final CoffeeAggregateRepo repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional
    @Override
    public DeleteCoffeeOutput execute(final Long aCommand) {
        final var coffee = this.repository
                .findByIdOptional(aCommand)
                .orElseThrow(() -> new RuntimeException("coffee id not found"));

        final var response = CoffeeResponse.with(coffee);
        this.repository.deleteById(coffee.getId());
        return new DeleteCoffeeOutput(response);
    }
}
