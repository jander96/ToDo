package todo.framework

data class Label(
    val id: String,
    val name: String,
    val color: String?= null,
    val order: Int? = null,
    val isFavorite: Boolean? = null
)
