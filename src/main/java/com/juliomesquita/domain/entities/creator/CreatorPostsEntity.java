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

   @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<PostCommentsEntity> comments;

   public static CreatorPostsEntity create(final String title, final String description, final String fileUrl) {
      final var recipes = new ArrayList<RecipeAggregate>();
      final var comments = new ArrayList<PostCommentsEntity>();
      return new CreatorPostsEntity(title, description, fileUrl, null, recipes, comments);
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

   public static CreatorPostsEntity getInstanceOnlyId(final Long id) {
      return new CreatorPostsEntity(id);
   }

   private CreatorPostsEntity(
       final String title,
       final String description,
       final String fileUrl,
       final CreatorAggregate creator,
       final List<RecipeAggregate> recipes,
       final List<PostCommentsEntity> comments
   ) {
      this.title = title;
      this.description = description;
      this.fileUrl = fileUrl;
      this.creator = creator;
      this.recipes = recipes;
      this.comments = comments;
   }

   private CreatorPostsEntity(final Long id) {
      this.id = id;
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

   public List<PostCommentsEntity> getComments() {
      return comments;
   }
}
