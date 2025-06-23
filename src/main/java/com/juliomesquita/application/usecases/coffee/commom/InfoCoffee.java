package com.juliomesquita.application.usecases.coffee.commom;

import com.juliomesquita.domain.enums.RoastingLevel;

import java.time.OffsetDateTime;

public record InfoCoffee(
        String name, String species, String sizeType, RoastingLevel roastingLevel,
        OffsetDateTime roastingDate, Integer points, String sensory
) {
}
