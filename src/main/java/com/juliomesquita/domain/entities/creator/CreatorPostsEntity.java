package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.entities.recipe.RecipeAggregate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tb_creator_posts")
public class CreatorPostsEntity extends PanacheEntity {

   private String title;

   private String description;

   private String fileUrl;

   @ManyToOne()
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;

   @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
   private List<RecipeAggregate> recipes;


   private OffsetDateTime createdAt;

   private OffsetDateTime updatedAt;
}
