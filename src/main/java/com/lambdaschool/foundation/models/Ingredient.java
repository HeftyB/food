package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ingredients")
public class Ingredient extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    @NotNull
    @Column(unique = true)
    private String name;

    private String description;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "categoryid")
    @JsonIgnoreProperties(value = "ingredients", allowSetters = true)
    private Category category;

    public Ingredient()
    {
    }

    public Ingredient(
        @NotNull String name,
        @NotNull Category category)
    {
        this.name = name;
        this.category = category;
    }

    public Ingredient(
        @NotNull String name,
        String description,
        @NotNull Category category)
    {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public long getIngredientid()
    {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid)
    {
        this.ingredientid = ingredientid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "Ingredient{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", category=" + category +
            '}';
    }
}
