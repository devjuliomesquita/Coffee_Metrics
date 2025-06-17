package com.juliomesquita.domain.entities.creator;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_region")
public class RegionEntity extends PanacheEntity { // Verificar a possíbilidade de colocar como value object

   private String country;
}
