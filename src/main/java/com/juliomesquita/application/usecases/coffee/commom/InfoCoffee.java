package com.juliomesquita.application.usecases.coffee.commom;

import com.juliomesquita.domain.enums.RoastingLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record InfoCoffee(
        @NotBlank(message = "Nome é obrigatório") String name,
        String species,
        String sizeType,
        @NotBlank(message = "Nível de torra é obrigatório")RoastingLevel roastingLevel,
        @Past OffsetDateTime roastingDate,
        @Positive Integer points,
        @Size(max = 500) String sensory
) {
}
