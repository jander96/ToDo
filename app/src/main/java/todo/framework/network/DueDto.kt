package todo.framework.network

import com.squareup.moshi.Json

data class DueDto(
    val string: String,
    val date: String,
    @Json(name = "is_recurring") val isRecurring: Boolean,
    val datetime: String,
    val timezone: String
)