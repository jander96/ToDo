package todo.framework

data class Project(
    val id: String,
    val name: String,
    val color: String,
    val parentId: String,
    val order: Int,
    val commentCount: Int,
    val isShared: Boolean,
    val isFavorite: Boolean,
    val isInboxProject: Boolean,
    val isTeamInbox: Boolean,
    val viewStyle: String,
    val url: String
)