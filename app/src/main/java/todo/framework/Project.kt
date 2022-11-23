package todo.framework

data class Project(
    val id: String,
    val name: String,
    val color: String? = null,
    val parentId: String?= null,
    val order: Int? = null,
    val commentCount: Int?= null,
    val isShared: Boolean? = null,
    val isFavorite: Boolean?= null,
    val isInboxProject: Boolean?= null,
    val isTeamInbox: Boolean?= null,
    val viewStyle: String?= null,
    val url: String? = null
)