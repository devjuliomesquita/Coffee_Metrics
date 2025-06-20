package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_comments")
public class PostCommentsEntity extends BaseEntityWithGeneratedId {

   @Column(name = "comment", nullable = false)
   private String comment;

   @ManyToOne
   @JoinColumn(name = "creator_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_comments_creator"))
   private CreatorAggregate creator;

   @ManyToOne
   @JoinColumn(name = "post_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_comments_post"))
   private CreatorPostsEntity post;

   @ManyToOne
   @JoinColumn(name = "comment_bind_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_comments_comment_bind"))
   private PostCommentsEntity commentBind;

   @OneToMany(mappedBy = "commentBind", cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<PostCommentsEntity> commentsResponse;

   public static PostCommentsEntity create(final String comment, final Long creatorId, final Long postId) {
      final var creator = CreatorAggregate.getInstanceOnlyId(creatorId);
      final var post = CreatorPostsEntity.getInstanceOnlyId(postId);
      final var comments = new HashSet<PostCommentsEntity>();
      return new PostCommentsEntity(comment, creator, post, null, comments);
   }

   public PostCommentsEntity update(final String comment) {
      this.comment = comment;
      return this;
   }

   public PostCommentsEntity bindToComment(final Long commentId) {
      this.commentBind = getInstanceOnlyId(commentId);
      return this;
   }

   public static PostCommentsEntity getInstanceOnlyId(final Long id) {
      return new PostCommentsEntity(id);
   }

   private PostCommentsEntity(
       final String comment,
       final CreatorAggregate creator,
       final CreatorPostsEntity post,
       final PostCommentsEntity commentBind,
       final Set<PostCommentsEntity> commentsResponse
   ) {
      this.comment = comment;
      this.creator = creator;
      this.post = post;
      this.commentBind = commentBind;
      this.commentsResponse = commentsResponse;
   }

   private PostCommentsEntity(final Long id) {
      this.id = id;
   }

   protected PostCommentsEntity() {
   }

   public String getComment() {
      return comment;
   }

   public CreatorAggregate getCreator() {
      return creator;
   }

   public CreatorPostsEntity getPost() {
      return post;
   }

   public PostCommentsEntity getCommentBind() {
      return commentBind;
   }

   public Set<PostCommentsEntity> getCommentsResponse() {
      return commentsResponse;
   }
}
