package todo.framework.network

import com.squareup.moshi.Json

data class TaskDto(
    val id:String,
    @Json(name ="project_id")val projectId:String?,
    @Json(name = "section_id")val sectionId:String?,
    val content:String,
    val description:String?,
    @Json(name = "is_completed") val isCompleted:Boolean?,
    val labels : Array<String?>?,
    @Json(name = "parent_id")val parentId:String?,
    val order:Int?,
    val priority:Int?,
    @Json(name = "due_string") val string: String?,
    @Json(name = "due_date")val date: String?,
    @Json(name = "due_datetime")val datetime: String?,
    @Json(name = "due_timezone")val timezone: String?,
    @Json(name ="comment_count") val commentCount: Int?,
    @Json(name = "created_at")val createdAt: String?,
    @Json(name = "creator_id")val creatorId:String?,
    @Json(name = "assignee_id") val assigneeId:String?,
    @Json(name ="assigner_id")val assignerId: String?
)