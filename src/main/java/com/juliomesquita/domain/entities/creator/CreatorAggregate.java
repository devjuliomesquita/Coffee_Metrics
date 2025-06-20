package com.juliomesquita.domain.entities.creator;

import com.juliomesquita.domain.commom.BaseEntityWithGeneratedId;
import com.juliomesquita.domain.entities.recipe.NotesEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_creator")
public class CreatorAggregate extends BaseEntityWithGeneratedId {

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "nick_name")
   private String nickname;

   @Column(name = "phone")
   private String phone;

   @Embedded
   @AttributeOverrides({
       @AttributeOverride(name = "country", column = @Column(name = "country"))
   })
   private RegionVO region;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
   private UserEntity user;

   @OneToOne(mappedBy = "creator")
   private CreatorFavoritesEntity favorites;

   @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
   private List<CreatorPostsEntity> posts;

   @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
   private Set<NotesEntity> notes;

   public static CreatorAggregate create(
       final String name, final String nickname, final String phone, final String region
   ) {
      final var posts = new ArrayList<CreatorPostsEntity>();
      final var notes = new HashSet<NotesEntity>();
      final var regionVO = new RegionVO(region);

      return new CreatorAggregate(name, nickname, phone, regionVO, null, null, posts, notes);
   }

   public CreatorAggregate update(
       final String name, final String nickname, final String phone, final String region
   ){
      this.region = new RegionVO(region);
      this.name = name;
      this.nickname = nickname;
      this.phone = phone;

      return this;
   }

   public CreatorAggregate addUser(final String email, final String password){
      final var userEntity = UserEntity.create(email, password);
      this.user = userEntity.bindToCreator(this);

      return this;
   }

   public CreatorAggregate inactive(){
      this.user.inactive();
      return this;
   }

   public static CreatorAggregate getInstanceOnlyId(final Long id){
      return new CreatorAggregate(id);
   }

   private CreatorAggregate(
       final String name,
       final String nickname,
       final String phone,
       final RegionVO region,
       final UserEntity user,
       final CreatorFavoritesEntity favorites,
       final List<CreatorPostsEntity> posts,
       final Set<NotesEntity> notes
   ) {
      this.name = name;
      this.nickname = nickname;
      this.phone = phone;
      this.region = region;
      this.user = user;
      this.favorites = favorites;
      this.posts = posts;
      this.notes = notes;
   }

   protected CreatorAggregate() {
   }

   private CreatorAggregate(final Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public String getNickname() {
      return nickname;
   }

   public String getPhone() {
      return phone;
   }

   public RegionVO getRegion() {
      return region;
   }

   public UserEntity getUser() {
      return user;
   }

   public CreatorFavoritesEntity getFavorites() {
      return favorites;
   }

   public List<CreatorPostsEntity> getPosts() {
      return posts;
   }

   public Set<NotesEntity> getNotes() {
      return notes;
   }
}
