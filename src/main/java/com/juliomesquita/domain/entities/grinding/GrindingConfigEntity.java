package com.juliomesquita.domain.entities.grinding;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "tb_grinding_config")
public class GrindingConfigEntity extends BaseEntityWithGeneratedId {

   private Integer clicks;

   @OneToOne
   @JoinColumn(name = "grinding_id", referencedColumnName = "id")
   private GrindingAggregate grinding;

   @ElementCollection
   @CollectionTable(
       name = "tb_grinding_config_interval",
       joinColumns = @JoinColumn(name = "grinding_config_id", referencedColumnName = "id")
   )
   @MapKeyColumn(name = "category_name")
   @AttributeOverrides({
       @AttributeOverride(name = "clickInitial", column = @Column(name = "click_initial")),
       @AttributeOverride(name = "clickFinal", column = @Column(name = "click_final"))
   })
   private Map<String, GrindingIntervalVO> intervals;
}
