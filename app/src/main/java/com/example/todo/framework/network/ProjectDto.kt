package com.example.todo.framework.network

import com.squareup.moshi.Json

data class ProjectDto(
    val id: String,
    val name: String,
    val color: String,
    @Json(name = "parent_id") val parentId: String,
    val order: Int,
    @Json(name = "comment_count") val commentCount: Int,
    @Json(name = "is_shared") val isShared: Boolean,
    @Json(name = "is_favorite") val isFavorite: Boolean,
    @Json(name = "is_inbox_project") val isInboxProject: Boolean,
    @Json(name = "is_team_inbox") val isTeamInbox: Boolean,
    @Json(name = "view_style") val viewStyle: String,
    val url: String,

)