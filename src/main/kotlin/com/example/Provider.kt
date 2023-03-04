package com.example

import com.example.recipe.RecipeRepository
import com.example.recipe.RecipeService

object Provider {

    val recipeRepository = RecipeRepository()
    val recipeService = RecipeService()

}