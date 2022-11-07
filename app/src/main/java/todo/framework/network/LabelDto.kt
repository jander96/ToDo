package todo.framework.network

import com.squareup.moshi.Json

data class LabelDto(
    val id: String,
    val name: String,
    val color: String,
    val order: Int,
    @Json(name = "is_favorite") val isFavorite: Boolean
)