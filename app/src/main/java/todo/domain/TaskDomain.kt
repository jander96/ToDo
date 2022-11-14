package todo.domain

data class TaskDomain(
    val id: String,
    val projectId: String,
    val sectionId: String,
    val content: String,
    val description: String,
    val isCompleted: Boolean,
    val labels: Array<String>,
    val parentId: String,
    val order: Int,
    val priority: Int,
    val due: DueDomain,
    val url: String,
    val commentCount: Int,
    val createdAt: String,
    val creatorId: String,
    val assigneeId: String,
    val assignerId: String
)