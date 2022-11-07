package todo.framework.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import todo.framework.network.DueDto

@Entity(tableName = "task_table")
data class TaskEntity(
    val id: String,
    @ColumnInfo(name = "project_id") val projectId: String,
    @ColumnInfo(name = "section_id") val sectionId: String,
    val content: String,
    val description: String,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean,
    val labels: Array<String>,
    @ColumnInfo(name = "parent_id") val parentId: String,
    val order: Int,
    val priority: Int,
    val due: DueDto,
    val url: String,
    @ColumnInfo(name = "comment_count") val commentCount: Int,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "creator_id") val creatorId: String,
    @ColumnInfo(name = "assignee_id") val assigneeId: String,
    @ColumnInfo(name = "assigner_id") val assignerId: String
)
