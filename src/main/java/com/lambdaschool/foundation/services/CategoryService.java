package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Category;

import java.util.List;

public interface CategoryService
{
    List<Category> findAll();

    Category findCategoryById(long id);

    Category save(Category cat);

    void delete(long id);
}
