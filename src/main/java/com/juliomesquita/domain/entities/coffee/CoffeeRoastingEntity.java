package com.juliomesquita.domain.entities.coffee;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_coffee_roasting")
public class CoffeeRoastingEntity extends BaseEntityWithGeneratedId {

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "region")
   private String region;

   @OneToMany(mappedBy = "roasting", fetch = FetchType.LAZY)
   private List<CoffeeAggregate> coffees;

   public static CoffeeRoastingEntity create(final String name, final String region) {
      final var coffees = new ArrayList<CoffeeAggregate>();
      return new CoffeeRoastingEntity(name, region, coffees);
   }

   public CoffeeRoastingEntity update(final String name, final String region) {
      this.name = name;
      this.region = region;

      return this;
   }

   public static CoffeeRoastingEntity getInstanceOnlyId(final Long id) {
      return new CoffeeRoastingEntity(id);
   }

   private CoffeeRoastingEntity(final String name, final String region, final List<CoffeeAggregate> coffees) {
      this.name = name;
      this.region = region;
      this.coffees = coffees;
   }

   private CoffeeRoastingEntity(final Long id) {
      this.id = id;
   }

   protected CoffeeRoastingEntity() {
   }

   public String getName() {
      return name;
   }

   public String getRegion() {
      return region;
   }

   public List<CoffeeAggregate> getCoffees() {
      return coffees;
   }
}
