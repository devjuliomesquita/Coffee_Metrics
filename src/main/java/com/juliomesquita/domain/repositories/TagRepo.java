package com.juliomesquita.domain.repositories;

import com.juliomesquita.domain.entities.recipe.TagEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TagRepo implements PanacheRepository<TagEntity> {
}
