package todo.domain

import com.squareup.moshi.Json

data class TaskDomain(
    val id: String,
    val projectId: String?,
    val sectionId: String?,
    val content: String,
    val description: String?,
    val isCompleted: Boolean?,
    val labels: Array<String?>?,
    val parentId: String?,
    val order: Int?,
    val priority: Int?,
    val string: String?,
    val date: String?,
    val datetime: String?,
    val timezone: String?,
    val commentCount: Int?,
    val createdAt: String?,
    val creatorId: String?,
    val assigneeId: String?,
    val assignerId: String?
)