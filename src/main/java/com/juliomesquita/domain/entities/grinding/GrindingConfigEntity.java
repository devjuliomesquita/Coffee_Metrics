package com.juliomesquita.domain.entities.grinding;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.HashMap;
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

   public static GrindingConfigEntity create(final Integer clicks, final Long grindingId){
      final var intervals = new HashMap<String, GrindingIntervalVO>();
      final var grinding = GrindingAggregate.getInstanceOnlyId(grindingId);

      return new GrindingConfigEntity(clicks, grinding, intervals);
   }

   public GrindingConfigEntity addIntervals(final String categoryName, final GrindingIntervalVO interval){
      this.intervals.put(categoryName, interval);

      return this;
   }

   private GrindingConfigEntity(final Integer clicks, final GrindingAggregate grinding, final Map<String, GrindingIntervalVO> intervals) {
      this.clicks = clicks;
      this.grinding = grinding;
      this.intervals = intervals;
   }

   protected GrindingConfigEntity() {
   }

   public Integer getClicks() {
      return clicks;
   }

   public GrindingAggregate getGrinding() {
      return grinding;
   }

   public Map<String, GrindingIntervalVO> getIntervals() {
      return intervals;
   }
}
