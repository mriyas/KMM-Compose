package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("recipes")
    var list: List<Recipe>
)
@Serializable
data class Recipe (
    @SerialName("id")
    var id: Int=0,
    @SerialName("name")
    var name: String="",
    @SerialName("instructions")
    val instructions: List<String> = arrayListOf(),
    @SerialName("tags")
    val tags: List<String> = arrayListOf(),
    @SerialName("ingredients")
    val ingredients: List<String> = arrayListOf(),
    @SerialName("prepTimeMinutes")
    val prepTimeMinutes: Int=0,
    @SerialName("cookTimeMinutes")
    val cookTimeMinutes: Int=0,
    @SerialName("servings")
    val servings: Int=0,
    @SerialName("difficulty")
    val difficulty: String="",
    @SerialName("cuisine")
    val cuisine: String="",
    @SerialName("caloriesPerServing")
    val caloriesPerServing: Int=0,
    @SerialName("rating")
    val rating:  Double=0.0,
    @SerialName("reviewCount")
    val reviewCount: Double=0.0,
    @SerialName("image")
    val image: String="",
)