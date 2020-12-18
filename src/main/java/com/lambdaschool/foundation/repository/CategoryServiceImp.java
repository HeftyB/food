package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Category;
import com.lambdaschool.foundation.models.Ingredient;
import com.lambdaschool.foundation.services.CategoryService;
import com.lambdaschool.foundation.services.UserAuditing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "categoryService")
public class CategoryServiceImp implements CategoryService
{
    @Autowired
    CategoryRepository catrepo;

    @Override
    public List<Category> findAll()
    {
        List<Category> catList = new ArrayList<>();

        catrepo.findAll().iterator()
            .forEachRemaining(catList::add);
        return catList;
    }

    @Override
    public Category findCategoryById(long id)
    {
        return catrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category id " + id + " not found!"));
    }

    @Override
    public Category save(Category cat)
    {
        Category category = new Category();

        category.getIngredients().clear();

        category.setName(cat.getName());
        for (Ingredient i : cat.getIngredients())
        {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientid(i.getIngredientid());
            ingredient.setName(i.getName());
            ingredient.setDescription(i.getDescription());
            ingredient.setCategory(i.getCategory());
            ingredient.setRecipes(i.getRecipes());

        }

        return catrepo.save(category);
    }

    @Override
    public void delete(long id)
    {
        catrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category id " + id + " not found!"));
    }
}
