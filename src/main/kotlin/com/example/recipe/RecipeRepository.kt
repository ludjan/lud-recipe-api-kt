package com.example.recipe

import com.example.recipe.model.Recipe

class RecipeRepository {

    private val recipes = mutableListOf<Recipe>()

    init {

        recipes.add(exampleRecipe1())
    }

    fun getRecipes() = recipes

    fun getRecipeById(id: Long) = recipes.find { recipe -> recipe.id == id }

    fun getRecipesEmpty() = listOf<Recipe>()

    fun insertRecipe(recipe: Recipe): Recipe {
        recipes.add(recipe)
        return recipe
    }
}