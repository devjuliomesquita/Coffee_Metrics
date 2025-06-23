package com.juliomesquita.domain.repositories;

import com.juliomesquita.domain.entities.coffee.CoffeeAggregate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoffeeAggregateRepo implements PanacheRepository<CoffeeAggregate> {
}
