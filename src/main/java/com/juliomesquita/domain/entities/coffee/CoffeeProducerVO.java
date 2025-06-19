package com.juliomesquita.domain.entities.coffee;

import jakarta.persistence.Embeddable;

@Embeddable
public record CoffeeProducerVO(
    String nameProducer,
    String region,
    Integer altitude
) {
   public static CoffeeProducerVO create(final String nameProducer, final String region, final Integer altitude){
      return new CoffeeProducerVO(nameProducer, region, altitude);
   }
}
