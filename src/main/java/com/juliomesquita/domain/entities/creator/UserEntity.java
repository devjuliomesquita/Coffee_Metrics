package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.enums.UserStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class UserEntity extends PanacheEntity {

   private String email;

   private String password;

   @Enumerated(EnumType.STRING)
   private UserStatus status;

   @MapsId()
   @OneToOne(mappedBy = "user")
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;
}
