package com.juliomesquita.domain.entities.creator;

import jakarta.persistence.Embeddable;

@Embeddable
public record RegionVO(String country) {
}
