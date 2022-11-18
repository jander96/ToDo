package todo.framework.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="project_table")
data class ProjectEntity(
    @PrimaryKey val id: String,
    val name: String,
    val color: String?,
    val order: Int?,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean?,
    @ColumnInfo(name = "is_inbox_project") val isInboxProject: Boolean?,
    val url: String?
)
