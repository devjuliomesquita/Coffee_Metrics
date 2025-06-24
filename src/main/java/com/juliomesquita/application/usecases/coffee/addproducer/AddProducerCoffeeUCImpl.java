package com.juliomesquita.application.usecases.coffee.addproducer;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.repositories.CoffeeAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class AddProducerCoffeeUCImpl extends AddProducerCoffeeUC {
    private final CoffeeAggregateRepo repository;

    public AddProducerCoffeeUCImpl(final CoffeeAggregateRepo repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional
    @Override
    public AddProducerCoffeeOutput execute(final AddProducerCoffeeInput aCommand) {
        final var coffee = this.repository
                .findByIdOptional(aCommand.coffeeId())
                .orElseThrow(() -> new RuntimeException("coffee id not found"))
                .addProducer(aCommand.producer().nameProducer(),aCommand.producer().region(),aCommand.producer().altitude());

        this.repository.persist(coffee);
        return new AddProducerCoffeeOutput(CoffeeResponse.with(coffee));
    }
}
