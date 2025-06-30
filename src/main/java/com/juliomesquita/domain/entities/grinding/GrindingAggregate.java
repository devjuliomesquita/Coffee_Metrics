package com.juliomesquita.domain.entities.grinding;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.creator.CreatorFavoritesEntity;
import com.juliomesquita.domain.entities.recipe.NotesEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_grinding")
public class GrindingAggregate extends BaseEntityWithGeneratedId {

   @Column(name = "brand", nullable = false)
   private String brand;

   @Column(name = "model")
   private String model;

   @Column(name = "clicks")
   private Integer clicks;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   private GrindingConfigEntity interval;

   @ManyToMany(fetch = FetchType.LAZY, mappedBy = "grindings")
   private List<NotesEntity> notes;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "grinding")
   private Set<CreatorFavoritesEntity> favorites;


   public static GrindingAggregate create(final String brand, final String model, final Integer clicks) {
      final var notes = new ArrayList<NotesEntity>();
      final var favorites = new HashSet<CreatorFavoritesEntity>();

      return new GrindingAggregate(brand, model, clicks, null, notes, favorites);
   }

   public GrindingAggregate update(final String brand, final String model, final Integer clicks) {
      this.brand = brand;
      this.model = model;
      this.clicks = clicks;

      return this;
   }

   public GrindingAggregate addConfig(final GrindingConfigEntity interval) {
      this.interval = interval;
      return this;
   }

   public static GrindingAggregate getInstanceOnlyId(final Long id) {
      return new GrindingAggregate(id);
   }

   private GrindingAggregate(final Long id) {
      this.id = id;
   }

   private GrindingAggregate(
       final String brand,
       final String model,
       final Integer clicks,
       final GrindingConfigEntity interval,
       final List<NotesEntity> notes,
       final Set<CreatorFavoritesEntity> favorites
   ) {
      this.brand = brand;
      this.model = model;
      this.clicks = clicks;
      this.interval = interval;
      this.notes = notes;
      this.favorites = favorites;
   }

   protected GrindingAggregate() {
   }

   public String getBrand() {
      return brand;
   }

   public String getModel() {
      return model;
   }

   public Integer getClicks() {
      return clicks;
   }

   public GrindingConfigEntity getInterval() {
      return interval;
   }

   public List<NotesEntity> getNotes() {
      return notes;
   }

   public Set<CreatorFavoritesEntity> getFavorites() {
      return favorites;
   }
}
