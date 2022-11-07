package todo.framework.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "label_table")
data class LabelEntity(
    val id: String,
    val name: String,
    val color: String,
    val order: Int,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)