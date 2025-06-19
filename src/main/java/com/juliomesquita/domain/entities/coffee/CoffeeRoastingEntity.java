package com.juliomesquita.domain.entities.coffee;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "tb_coffee_roasting")
public class CoffeeRoastingEntity extends BaseEntityWithGeneratedId {

   private String name;

   private String region;

   @OneToMany(mappedBy = "roasting",fetch = FetchType.LAZY)
   private List<CoffeeAggregate> coffees;
}
