package todo.domain

data class DueDomain(
    val string: String,
    val date: String,
    val isRecurring: Boolean,
    val datetime: String,
    val timezone: String
)