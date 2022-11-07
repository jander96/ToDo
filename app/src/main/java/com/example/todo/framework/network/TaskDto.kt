package com.example.todo.framework.network

data class TaskDto(
    val id:String,
    val project_id:String,
    val section_id:String,
    val content:String,
    val description:String,
    val is_completed:Boolean,
    val labels : Array<String>,
    val parent_id:String,
    val order:Int,
    val priority:Int,
    val due: DueDto,
    val url :String,
    val comment_count: Int,
    val created_at: String,
    val creator_id:String,
    val assignee_id:String,
    val assigner_id: String
)