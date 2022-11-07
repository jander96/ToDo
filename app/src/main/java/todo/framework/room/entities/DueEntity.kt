package todo.framework.room.entities

import androidx.room.ColumnInfo





data class DueEmbeddeble(
    val string: String,
    val date: String,
    @ColumnInfo(name = "is_recurring") val isRecurring: Boolean,
    val datetime: String,
    val timezone: String
)
