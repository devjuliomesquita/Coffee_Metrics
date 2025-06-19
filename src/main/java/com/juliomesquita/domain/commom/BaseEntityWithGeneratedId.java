package com.juliomesquita.domain.commom;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntityWithGeneratedId extends PanacheEntityBase {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true, nullable = false)
   private Long id;

   @Version
   private Long version;

   @Column(name = "created_at", nullable = false, updatable = false)
   private OffsetDateTime createdAt;

   @Column(name = "updated_at", nullable = false)
   private OffsetDateTime updatedAt;

   @PrePersist
   protected void onCreate() {
      final OffsetDateTime now = OffsetDateTime.now();
      this.createdAt = now;
      this.updatedAt = now;
   }

   @PreUpdate
   protected void onUpdate() {
      this.updatedAt = OffsetDateTime.now();
   }

   public Long getId() {
      return id;
   }

   public Long getVersion() {
      return version;
   }

   public OffsetDateTime getCreatedAt() {
      return createdAt;
   }

   public OffsetDateTime getUpdatedAt() {
      return updatedAt;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      BaseEntityWithGeneratedId that = (BaseEntityWithGeneratedId) o;
      return Objects.equals(id, that.id);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }
}
