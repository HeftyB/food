package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Recipe;
import com.lambdaschool.foundation.models.RecipeIngredient;
import com.lambdaschool.foundation.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImp
    implements RecipeService
{
    @Autowired
    private RecipeRepository recrepo;

    @Override
    public List<Recipe> findAll()
    {
        List<Recipe> list = new ArrayList<>();

        recrepo.findAll().iterator()
            .forEachRemaining(list::add);

        return list;
    }

    @Override
    public List<Recipe> findByNameContaining(String name)
    {
        return recrepo.findByRecipenameContainingIgnoreCase(name);
    }

    @Override
    public Recipe findRecipeById(long id)
    {
        return recrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));
    }

    @Override
    public Recipe save(Recipe recipe)
    {
        Recipe r = new Recipe();

        r.setRecipename(recipe.getRecipename());
        r.setDescription(recipe.getDescription());
        r.setDirections(recipe.getDirections());

        r.getIngredients().clear();

        for (RecipeIngredient rec : recipe.getIngredients())
        {
            r.getIngredients().add(new RecipeIngredient(r, rec.getIngredient(),
                rec.getQty()));
        }

        return recrepo.save(r);
    }

    @Override
    public Recipe update(
        Recipe recipe,
        long id)
    {
        Recipe r = findRecipeById(id);

        if (recipe.getRecipename() != null)
        {
            r.setRecipename(recipe.getRecipename());
        }

        if (recipe.getDescription() != null)
        {
            r.setDescription(recipe.getDescription());
        }

        if (recipe.getDirections() != null)
        {
            r.setDirections(recipe.getDirections());
        }


        if (recipe.getIngredients().size() > 0)
        {
            r.getIngredients().clear();

            for (RecipeIngredient rec : recipe.getIngredients())
            {
                r.getIngredients().add(new RecipeIngredient(r, rec.getIngredient(),
                    rec.getQty()));
            }
        }


        return recrepo.save(r);
    }

    @Override
    public void delete(long id)
    {
        recrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));
        recrepo.deleteById(id);
    }
}
