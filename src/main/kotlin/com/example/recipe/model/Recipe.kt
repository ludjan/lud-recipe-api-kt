package com.example.recipe.model

class Recipe (
    val id: Long,
    val title: String,
    val description: String,
    val portions: Int,
    val ingredientQuantityUnits: List<IngredientQuantityUnit>,
    val steps: List<Step>
) {
    fun getIngredients() = ingredientQuantityUnits.map { it.ingredient }
}