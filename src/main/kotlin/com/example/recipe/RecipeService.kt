package com.example.recipe

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.core.rightIfNotNull
import com.example.Provider
import com.example.recipe.model.FacadeRecipe
import com.example.recipe.model.Recipe
import io.ktor.server.plugins.NotFoundException

class RecipeService {

    private val recipeRepository = Provider.recipeRepository

    fun getEvenRecipes() =
        recipeRepository.getRecipes()
            .filter { recipe ->
                recipe.id % 2 == 0L
            }
    fun getOddRecipes(): Either<NotFoundException, List<Recipe>> {
        val recipes = recipeRepository.getRecipesEmpty()
        return if (recipes.isEmpty()) { NotFoundException().left() }
            else { recipes.right() }
    }

    fun getRecipeById(id: Long) : Either<NotFoundException, Recipe> =
        recipeRepository.getRecipeById(id).rightIfNotNull { NotFoundException() }

    fun createRecipe(facadeRecipe: FacadeRecipe): Either<Exception, Recipe> {
        val nextAvailableId: Long = recipeRepository.getRecipes().maxOf { it.id } + 1
        println("The next available id is $nextAvailableId")
        val recipe = Recipe.fromFacade(nextAvailableId, facadeRecipe)
        return recipeRepository.insertRecipe(recipe).right()
    }
}