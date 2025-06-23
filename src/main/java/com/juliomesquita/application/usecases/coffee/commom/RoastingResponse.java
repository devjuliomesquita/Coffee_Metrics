package com.juliomesquita.application.usecases.coffee.commom;

import com.juliomesquita.domain.entities.coffee.CoffeeRoastingEntity;

public record RoastingResponse(Long id, String name, String region) {
    public static RoastingResponse with(final CoffeeRoastingEntity entity) {
        return new RoastingResponse(entity.getId(), entity.getName(), entity.getRegion());
    }
}
