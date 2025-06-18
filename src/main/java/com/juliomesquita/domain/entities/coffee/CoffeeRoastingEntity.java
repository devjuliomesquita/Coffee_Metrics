package com.juliomesquita.domain.entities.coffee;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "tb_coffee_roasting")
public class CoffeeRoastingEntity extends PanacheEntity {

   private String name;

   private String region;

   @OneToMany(mappedBy = "roasting",fetch = FetchType.LAZY)
   private List<CoffeeAggregate> coffees;
}
