package com.juliomesquita.domain.entities.grinding;

import jakarta.persistence.Embeddable;

@Embeddable
public record GrindingIntervalVO(
    Integer clickInitial,
    Integer clickFinal
) {
   public static GrindingIntervalVO create(final Integer clickInitial, final Integer clickFinal) {
      return new GrindingIntervalVO(clickInitial, clickFinal);
   }
}
