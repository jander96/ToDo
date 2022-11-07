package todo.domain

data class DueDtoDomain(
    val string: String,
    val date: String,
    val isRecurring: Boolean,
    val datetime: String,
    val timezone: String
)