package com.juliomesquita.domain.entities.grinding;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record GrindingIntervalVO(
    @Column(name = "click_initial") Integer clickInitial,
    @Column(name = "click_final") Integer clickFinal
) {
   public static GrindingIntervalVO create(final Integer clickInitial, final Integer clickFinal) {
      return new GrindingIntervalVO(clickInitial, clickFinal);
   }
}
