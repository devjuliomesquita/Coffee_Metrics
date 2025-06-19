package com.juliomesquita.domain.entities.grinding;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.creator.CreatorFavoritesEntity;
import com.juliomesquita.domain.entities.recipe.NotesEntity;
import jakarta.persistence.*;

import javax.swing.*;
import java.util.Set;

@Entity
@Table(name = "tb_grinding")
public class GrindingAggregate extends BaseEntityWithGeneratedId {

   private Spring brand;

   private String model;

   private Integer clicks;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   private GrindingConfigEntity interval;

   @ManyToOne(fetch = FetchType.LAZY)
   private NotesEntity notes;

   @OneToMany(fetch = FetchType.LAZY)
   private Set<CreatorFavoritesEntity> favorites;
}
