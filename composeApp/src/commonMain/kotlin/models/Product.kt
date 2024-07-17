package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("products")
    var products: List<Product>,
)

@Serializable
data class Product(
    @SerialName("id")
    var id: Int = 0,
    @SerialName("title")
    var title: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("tags")
    val tags: List<String> = arrayListOf(),
    @SerialName("reviews")
    val reviews: List<Reviews> = arrayListOf(),
    @SerialName("price")
    val price: Double = 0.0,
    @SerialName("discountPercentage")
    val discountPercentage: Double = 0.0,
    @SerialName("brand")
    val brand: String = "",
    @SerialName("stock")
    val stock: Int = 0,
    @SerialName("rating")
    val rating: Double = 0.0,
    @SerialName("reviewCount")
    val reviewCount: Double = 0.0,
    @SerialName("images")
    val images: List<String> = arrayListOf(),
)

@Serializable
data class Reviews(
    @SerialName("rating")
    val rating: Int,
    @SerialName("comment")
    val comment: String = "",
    @SerialName("date")
    val date: String = "",
    @SerialName("reviewerName")
    val reviewerName: String = "",
    @SerialName("reviewerEmail")
    val reviewerEmail: String = "",
)