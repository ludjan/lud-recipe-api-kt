package com.example.recipe

import com.example.recipe.model.Ingredient
import com.example.recipe.model.IngredientQuantityUnit
import com.example.recipe.model.Recipe
import com.example.recipe.model.Step
import com.example.recipe.model.Unit

fun exampleRecipe1(): Recipe {

    val deciliter = Unit("deciliter")
    val justADash = Unit("dash", "dashes")
    val piece = Unit("piece", "pieces")

    return Recipe(
        1L,
        "Pancakes",
        "Delicious pancakes",
        3,
        listOf(
            IngredientQuantityUnit(
                Ingredient("Milk"),
                6,
                deciliter),
            IngredientQuantityUnit(
                Ingredient("Flour"),
                3,
                deciliter),
            IngredientQuantityUnit(
                Ingredient("Egg"),
                4,
                piece
            ),
            IngredientQuantityUnit(
                Ingredient("Salt"),
                1,
                justADash
            )

        ),
        listOf(
            Step(1, "Mix flour and half of the milk"),
            Step(2, "Mix in the rest of the milk"),
            Step(3, "Mix in the eggs"),
            Step(4, "Mix in the salt"),
            Step(5, "Fry the mix in a pan")
        ))
}