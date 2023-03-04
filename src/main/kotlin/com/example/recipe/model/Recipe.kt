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

    class Builder() {
        private var id: Long = 1L
        private var title: String = ""
        private var description: String = ""
        private var portions: Int = 0
        private var ingredientQuantityUnit : List<IngredientQuantityUnit> = listOf()
        private var steps : List<Step> = listOf()

        fun id(id: Long) = apply { this.id = id }
        fun title(title: String) = apply { this.title = title }
        fun description(description: String) = apply { this.description = description }
        fun portions(portions: Int) = apply { this.portions = portions }
        fun ingredientQuantityUnit(ingredientQuantityUnits: List<IngredientQuantityUnit>) =
            apply { this.ingredientQuantityUnit = ingredientQuantityUnits }
        fun steps(steps: List<Step>) = apply { this.steps = steps }
        fun build() = Recipe(id, title, description, portions, ingredientQuantityUnit, steps)
    }

    companion object {
        fun fromFacade (id: Long, facadeRecipe: FacadeRecipe) : Recipe =
            Builder()
                .id(id)
                .title(facadeRecipe.title)
                .description(facadeRecipe.title)
                .portions(facadeRecipe.portions)
                .ingredientQuantityUnit(facadeRecipe.ingredientQuantityUnits)
                .steps(facadeRecipe.steps)
                .build()
    }
}

data class FacadeRecipe(
    val title: String,
    val description: String,
    val portions: Int,
    val ingredientQuantityUnits: List<IngredientQuantityUnit>,
    val steps: List<Step>)