package com.example.recipe.model

data class Unit(
    val singularName: String,
    val pluralName: String
) {
    constructor(singularName: String): this(singularName, singularName)
}