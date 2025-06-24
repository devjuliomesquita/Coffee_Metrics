package com.juliomesquita.application.usecases.coffee.addroasting;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.entities.coffee.CoffeeRoastingEntity;
import com.juliomesquita.domain.repositories.CoffeeAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class AddRoastingCoffeeUCImpl extends AddRoastingCoffeeUC {
    private final CoffeeAggregateRepo repository;

    public AddRoastingCoffeeUCImpl(final CoffeeAggregateRepo repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional
    @Override
    public AddRoastingCoffeeOutput execute(final AddRoastingCoffeeInput aCommand) {
        final var coffee = this.repository
                .findByIdOptional(aCommand.coffeeId())
                .orElseThrow(() -> new RuntimeException("coffee id not found"));

        final Optional<CoffeeRoastingEntity> roastingOpt = CoffeeRoastingEntity.findByIdOptional(aCommand.roasting().id());

        if (roastingOpt.isEmpty()) {
            coffee.addRoasting(aCommand.roasting().name(), aCommand.roasting().region());
        } else {
            coffee.bindToRoasting(roastingOpt.get());
        }

        this.repository.persist(coffee);
        return new AddRoastingCoffeeOutput(CoffeeResponse.with(coffee));
    }
}
