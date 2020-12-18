package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Recipe;

import java.util.List;

public interface RecipeService
{
    List<Recipe> findAll();
    List<Recipe> findByNameContaining(String name);
    Recipe findRecipeById(long id);
    Recipe save(Recipe recipe);
    Recipe update(Recipe recipe, long id);
    void delete(long id);
}
