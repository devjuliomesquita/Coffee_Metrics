package com.juliomesquita.domain.entities.creator;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_units_system")
public class UnitsSystemEntity extends PanacheEntity {

   private String weight;

   private String temperature;

   private String measures;

   private String proportion;

   @MapsId
   @OneToOne(mappedBy = "unitsSystem")
   @JoinColumn(name = "favorite_id", referencedColumnName = "id")
   private CreatorFavoritesEntity favorites;
}
