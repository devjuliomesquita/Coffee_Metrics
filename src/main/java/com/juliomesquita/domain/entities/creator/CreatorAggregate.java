package com.juliomesquita.domain.entities.creator;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name= "tb_creator")
public class CreatorAggregate extends PanacheEntity {

   private String nome;

   private String nickname;

   private String telefone;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   private UserEntity user;

   @OneToOne(mappedBy = "creator")
   private CreatorFavorites favorites;
}
