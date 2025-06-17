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

   @OneToOne(mappedBy = "user")
   private CreatorAggregate creator;
}
