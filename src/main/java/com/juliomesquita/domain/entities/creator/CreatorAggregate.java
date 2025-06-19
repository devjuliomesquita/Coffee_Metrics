package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.recipe.NotesEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "tb_creator")
public class CreatorAggregate extends BaseEntityWithGeneratedId {

   private String nome;

   private String nickname;

   private String telefone;

   @Embedded
   private RegionVO region;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
   private UserEntity user;

   @OneToOne(mappedBy = "creator")
   private CreatorFavoritesEntity favorites;

   @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
   private List<CreatorPostsEntity> posts;

   @OneToOne(mappedBy = "creator", fetch = FetchType.LAZY)
   private NotesEntity notes;
}
