package com.lambdaschool.foundation.models;


import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "recipeingredient")
@IdClass(RecipeIngredientId.class)
public class RecipeIngredient extends Auditable implements Serializable
{


}
