package com.juliomesquita.domain.entities.coffee;

import com.juliomesquita.domain.entities.recipe.NotesEntity;
import com.juliomesquita.domain.enums.RoastingLevel;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_coffee")
public class CoffeeAggregate extends PanacheEntity {

   private String name;

   private String species;

   private String sizeType;

   @Enumerated(EnumType.STRING)
   private RoastingLevel roastingLevel;

   private OffsetDateTime roastingDate;

   private Integer points;

   private String sensory;

   @Embedded
   private CoffeeProducerVO producer;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "roasting_id", referencedColumnName = "id")
   private CoffeeRoastingEntity roasting;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "notes_id", referencedColumnName = "id")
   private NotesEntity notes;
}
