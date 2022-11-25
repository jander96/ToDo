package todo.framework

import todo.domain.DueDomain

data class Task(
    val id: String,
    val projectId: String?=null,
    val sectionId: String?=null,
    val content: String,
    val description: String?=null,
    val isCompleted: Boolean?=null,
    val labels: Array<String?>?=null,
    val parentId: String?=null,
    val order: Int? = null,
    val priority: Int? = null,
    val due: DueDomain? = null,
    val url: String? = null,
    val commentCount: Int? = null,
    val createdAt: String? = null,
    val creatorId: String? = null,
    val assigneeId: String? = null,
    val assignerId: String? = null
)
