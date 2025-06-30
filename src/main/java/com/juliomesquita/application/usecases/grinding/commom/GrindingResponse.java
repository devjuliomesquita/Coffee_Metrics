package com.juliomesquita.application.usecases.grinding.commom;

import com.juliomesquita.domain.entities.grinding.GrindingAggregate;

public record GrindingResponse(
    Long id,
    String brand,
    String model,
    Integer totalClicks,
    InfoConfig interval
) {
    public static GrindingResponse with(final GrindingAggregate entity) {
        return new GrindingResponse(
            entity.getId(), entity.getBrand(), entity.getModel(), entity.getClicks(), InfoConfig.with(entity.getInterval())
        );
    }
}
