package com.juliomesquita.domain.entities.coffee;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.recipe.NotesEntity;
import com.juliomesquita.domain.enums.RoastingLevel;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_coffee")
public class CoffeeAggregate extends BaseEntityWithGeneratedId {

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "species")
   private String species;

   @Column(name = "size_type")
   private String sizeType;

   @Enumerated(EnumType.STRING)
   @Column(name = "roasting_level", nullable = false)
   private RoastingLevel roastingLevel;

   @Column(name = "roasting_date")
   private OffsetDateTime roastingDate;

   @Column(name = "points")
   private Integer points;

   @Column(name = "sensory", nullable = false)
   private String sensory;

   @Embedded
   @AttributeOverrides({
       @AttributeOverride(name = "nameProducer", column = @Column(name = "name_producer")),
       @AttributeOverride(name = "region", column = @Column(name = "region")),
       @AttributeOverride(name = "altitude", column = @Column(name = "altitude"))
   })
   private CoffeeProducerVO producer;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   @JoinColumn(name = "roasting_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_coffee_roasting"))
   private CoffeeRoastingEntity roasting;

   @ManyToMany(fetch = FetchType.LAZY, mappedBy = "coffees")
   private List<NotesEntity> notes;

   public static CoffeeAggregate create(
       final String name, final String species, final String sizeType, final RoastingLevel roastingLevel,
       final OffsetDateTime roastingDate, final Integer points, final String sensory
   ) {
      final var notes = new ArrayList<NotesEntity>();

      return new CoffeeAggregate(
          name, species, sizeType, roastingLevel, roastingDate, points, sensory, null, null, notes);
   }

   public CoffeeAggregate update(
       final String name, final String species, final String sizeType, final RoastingLevel roastingLevel,
       final OffsetDateTime roastingDate, final Integer points, final String sensory
   ) {
      this.name = name;
      this.species = species;
      this.sizeType = sizeType;
      this.roastingLevel = roastingLevel;
      this.roastingDate = roastingDate;
      this.points = points;
      this.sensory = sensory;

      return this;
   }

   public CoffeeAggregate addProducer(final String nameProducer, final String region, final Integer altitude) {
      this.producer = CoffeeProducerVO.create(nameProducer, region, altitude);

      return this;
   }

   public CoffeeAggregate addRoasting(final String name, final String region) {
      this.roasting = CoffeeRoastingEntity.create(name, region);
      return this;
   }

   public static CoffeeAggregate getInstanceOnlyId(final Long id) {
      return new CoffeeAggregate(id);
   }

   private CoffeeAggregate(
       final String name,
       final String species,
       final String sizeType,
       final RoastingLevel roastingLevel,
       final OffsetDateTime roastingDate,
       final Integer points,
       final String sensory,
       final CoffeeProducerVO producer,
       final CoffeeRoastingEntity roasting,
       final List<NotesEntity> notes
   ) {
      this.name = name;
      this.species = species;
      this.sizeType = sizeType;
      this.roastingLevel = roastingLevel;
      this.roastingDate = roastingDate;
      this.points = points;
      this.sensory = sensory;
      this.producer = producer;
      this.roasting = roasting;
      this.notes = notes;
   }

   private CoffeeAggregate(final Long id) {
      this.id = id;
   }

   protected CoffeeAggregate() {
   }

   public String getName() {
      return name;
   }

   public String getSpecies() {
      return species;
   }

   public String getSizeType() {
      return sizeType;
   }

   public RoastingLevel getRoastingLevel() {
      return roastingLevel;
   }

   public OffsetDateTime getRoastingDate() {
      return roastingDate;
   }

   public Integer getPoints() {
      return points;
   }

   public String getSensory() {
      return sensory;
   }

   public CoffeeProducerVO getProducer() {
      return producer;
   }

   public CoffeeRoastingEntity getRoasting() {
      return roasting;
   }

   public List<NotesEntity> getNotes() {
      return notes;
   }
}
