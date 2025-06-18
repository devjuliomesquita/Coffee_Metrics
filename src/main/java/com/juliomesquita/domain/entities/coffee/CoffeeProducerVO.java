package com.juliomesquita.domain.entities.coffee;

import jakarta.persistence.Embeddable;

@Embeddable
public record CoffeeProducerVO(
    String nameProducer,
    String region,
    Integer altitude
) {
}
