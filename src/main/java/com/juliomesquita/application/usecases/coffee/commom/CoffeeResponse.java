package com.juliomesquita.application.usecases.coffee.commom;

import com.juliomesquita.domain.entities.coffee.CoffeeAggregate;
import com.juliomesquita.domain.entities.coffee.CoffeeProducerVO;
import com.juliomesquita.domain.enums.RoastingLevel;

import java.time.OffsetDateTime;

public record CoffeeResponse(
        Long id,
        String name,
        String species,
        String sizeType,
        RoastingLevel roastingLevel,
        OffsetDateTime roastingDate,
        Integer points,
        String sensory,
        CoffeeProducerVO producer,
        RoastingResponse roasting
) {
    public static CoffeeResponse with(final CoffeeAggregate agg) {
        return new CoffeeResponse(agg.getId(), agg.getName(), agg.getSpecies(), agg.getSizeType(), agg.getRoastingLevel(),
                agg.getRoastingDate(), agg.getPoints(), agg.getSensory(), agg.getProducer(), RoastingResponse.with(agg.getRoasting()));
    }


}
