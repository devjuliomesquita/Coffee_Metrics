package com.juliomesquita.domain.entities.grinding;

import jakarta.persistence.Embeddable;

@Embeddable
public record GrindingIntervalVO(
    Integer clickInitial,
    Integer clickFinal
) {
}
