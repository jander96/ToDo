package todo.domain

data class DueDomain(
    val string: String? = null,
    val date: String? = null,
    val isRecurring: Boolean? = null,
    val datetime: String? = null,
    val timezone: String? = null
)