package todo.domain

data class LabelDomain(
    val id: String,
    val name: String,
    val color: String?,
    val order: Int?,
    val isFavorite: Boolean?
)