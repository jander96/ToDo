package todo.framework

data class Label(
    val id: String,
    val name: String,
    val color: String?,
    val order: Int?,
    val isFavorite: Boolean?
)
