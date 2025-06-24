package com.juliomesquita.application.usecases.coffee.create;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.entities.coffee.CoffeeAggregate;
import com.juliomesquita.domain.repositories.CoffeeAggregateRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class CreateCoffeeUCImpl extends CreateCoffeeUC {

    private final CoffeeAggregateRepo repository;

    public CreateCoffeeUCImpl(final CoffeeAggregateRepo repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Transactional
    @Override
    public CreateCoffeeOutput execute(final CreateCoffeeInput aCommand) {
        final var coffee = CoffeeAggregate
                .create(
                        aCommand.infoCoffee().name(), aCommand.infoCoffee().species(), aCommand.infoCoffee().sizeType(),
                        aCommand.infoCoffee().roastingLevel(), aCommand.infoCoffee().roastingDate(),
                        aCommand.infoCoffee().points(), aCommand.infoCoffee().sensory())
                .addProducer(aCommand.infoProducer().nameProducer(), aCommand.infoProducer().region(), aCommand.infoProducer().altitude())
                .addRoasting(aCommand.infoRoasting().name(), aCommand.infoRoasting().region());

        this.repository.persist(coffee);
        return new CreateCoffeeOutput(CoffeeResponse.with(coffee));
    }
}
