package todo.framework.room.entities

import androidx.room.ColumnInfo
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "project_id") val projectId: String?,
    val content: String,
    val description: String?,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean?,
    val labels: Array<String?>?,
    val order: Int?,
    val priority: Int?,
    @Embedded val due: DueEmbeddeble?,
    val url: String?,
)
