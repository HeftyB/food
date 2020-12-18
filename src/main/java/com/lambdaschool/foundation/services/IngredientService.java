package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Ingredient;

import java.util.List;

public interface IngredientService
{
    List<Ingredient> findAll();

    List<Ingredient> findByNameContaining(String name);

    Ingredient findIngredientById(long id);

    Ingredient save(Ingredient ingredient);

    Ingredient update(
        Ingredient ingredient,
        long id);

    void delete(long id);
}
