package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.commom.BaseEntityWithManualId;
import com.juliomesquita.domain.enums.UserStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class UserEntity extends BaseEntityWithManualId {

   @Column(name = "email", nullable = false, unique = true)
   private String email;

   @Column(name = "password", nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(name = "status", nullable = false)
   private UserStatus status;

   @MapsId()
   @OneToOne
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;

   public static UserEntity create(final String email, final String password) {
      return new UserEntity(email, password, UserStatus.ACTIVE, null);
   }

   public UserEntity inactive() {
      this.status = UserStatus.INACTIVE;
      return this;
   }

   public UserEntity bindToCreator(final CreatorAggregate creator) {
      this.creator = creator;

      return this;
   }

   private UserEntity(
       final String email,
       final String password,
       final UserStatus status,
       final CreatorAggregate creator
   ) {
      this.email = email;
      this.password = password;
      this.status = status;
      this.creator = creator;
   }

   protected UserEntity() {
   }

   public String getEmail() {
      return email;
   }

   public String getPassword() {
      return password;
   }

   public UserStatus getStatus() {
      return status;
   }

   public CreatorAggregate getCreator() {
      return creator;
   }
}
