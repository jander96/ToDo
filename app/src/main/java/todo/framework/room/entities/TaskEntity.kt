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
    @ColumnInfo(name = "project_id")val projectId: String?,
    @ColumnInfo(name = "section_id")val sectionId: String?,
    val content: String,
    val description: String?,
    @ColumnInfo(name = "is_completed")val isCompleted: Boolean?,
    val labels: Array<String>? ,
    @ColumnInfo(name = "parent_id")val parentId: String?,
    val order: Int?,
    val priority: Int?,
    val string: String?,
    val date: String? ,
    val datetime: String?,
    val timezone: String?,
    @ColumnInfo(name = "comment_count")val commentCount: Int?,
    @ColumnInfo(name = "created_at")val createdAt: String?,
    @ColumnInfo(name = "creator_id")val creatorId: String?,
    @ColumnInfo(name = "assigneed_id")val assigneeId: String?,
    @ColumnInfo(name = "assigner_id")val assignerId: String?
)
