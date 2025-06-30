package com.juliomesquita.domain.repositories;

import com.juliomesquita.domain.entities.grinding.GrindingAggregate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GrindingAggregateRepo implements PanacheRepository<GrindingAggregate> {
}
