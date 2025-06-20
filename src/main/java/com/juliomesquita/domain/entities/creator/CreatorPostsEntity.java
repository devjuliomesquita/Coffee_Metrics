package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.recipe.RecipeAggregate;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_creator_posts")
public class CreatorPostsEntity extends BaseEntityWithGeneratedId {

   @Column(name = "title", nullable = false)
   private String title;

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "file_url")
   private String fileUrl;

   @ManyToOne()
   @JoinColumn(name = "creator_id", referencedColumnName = "id")
   private CreatorAggregate creator;

   @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
   private List<RecipeAggregate> recipes;

   public static CreatorPostsEntity create(final String title, final String description, final String fileUrl) {
      final var recipes = new ArrayList<RecipeAggregate>();
      return new CreatorPostsEntity(title, description, fileUrl, null, recipes);
   }

   public CreatorPostsEntity update(final String title, final String description, final String fileUrl) {
      this.title = title;
      this.description = description;
      this.fileUrl = fileUrl;

      return this;
   }

   public CreatorPostsEntity bindToCreator(final Long creatorId) {
      this.creator = CreatorAggregate.getInstanceOnlyId(creatorId);
      return this;
   }

   private CreatorPostsEntity(
       final String title,
       final String description,
       final String fileUrl,
       final CreatorAggregate creator,
       final List<RecipeAggregate> recipes
   ) {
      this.title = title;
      this.description = description;
      this.fileUrl = fileUrl;
      this.creator = creator;
      this.recipes = recipes;
   }

   protected CreatorPostsEntity() {
   }

   public String getTitle() {
      return title;
   }

   public String getDescription() {
      return description;
   }

   public String getFileUrl() {
      return fileUrl;
   }

   public CreatorAggregate getCreator() {
      return creator;
   }

   public List<RecipeAggregate> getRecipes() {
      return recipes;
   }
}
