package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_units_system")
public class UnitsSystemEntity extends BaseEntityWithGeneratedId {

   @Column(name = "weight")
   private String weight;

   @Column(name = "temperature")
   private String temperature;

   @Column(name = "measures")
   private String measures;

   @Column(name = "proportion")
   private String proportion;

   @MapsId
   @OneToOne
   @JoinColumn(name = "favorite_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_units_system_favorites"))
   private CreatorFavoritesEntity favorites;

   public static UnitsSystemEntity create(
       final String weight, final String temperature, final String measures, final String proportion
   ) {
      return new UnitsSystemEntity(weight, temperature, measures, proportion, null);
   }

   public UnitsSystemEntity update(
       final String weight, final String temperature, final String measures, final String proportion
   ) {
      this.weight = weight;
      this.temperature = temperature;
      this.measures = measures;
      this.proportion = proportion;

      return this;
   }

   public UnitsSystemEntity bindToFavorites(final CreatorFavoritesEntity favorites){
      this.favorites = favorites;

      return this;
   }
   private UnitsSystemEntity(
       final String weight,
       final String temperature,
       final String measures,
       final String proportion,
       final CreatorFavoritesEntity favorites
   ) {
      this.weight = weight;
      this.temperature = temperature;
      this.measures = measures;
      this.proportion = proportion;
      this.favorites = favorites;
   }

   protected UnitsSystemEntity() {
   }

   public String getWeight() {
      return weight;
   }

   public String getTemperature() {
      return temperature;
   }

   public String getMeasures() {
      return measures;
   }

   public String getProportion() {
      return proportion;
   }

   public CreatorFavoritesEntity getFavorites() {
      return favorites;
   }
}