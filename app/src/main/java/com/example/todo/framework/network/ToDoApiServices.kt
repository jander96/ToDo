package com.example.todo.framework.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ToDoApiServices {
    //Projects
    @GET("projects")
    suspend fun getAllProjects(): List<ProjectDto>

    @POST("projects")
    suspend fun createProject(@Body projectDto: ProjectDto)

    @GET("projects/{id}")
    suspend fun getProjectById(@Path("id") id: String): ProjectDto

    @POST("projects/{id}")
    suspend fun updateProject(@Path("id") id: String, @Body projectDto: ProjectDto)

    @GET("projects/{id}/collaborators")
    suspend fun getAllProjectCollaborators(@Path("id") id: String): List<CollaboratorsDto>

    //Task
    @GET("api.todoist.com/rest/v2/tasks")
    suspend fun getActiveTasks(): List<TaskDto>

    @POST
    suspend fun createNewTask(@Body task: TaskDto)

    @GET("tasks/{id}")
    suspend fun getAnActiveTaskById()

    @POST("tasks/{id}")
    suspend fun updateTask(@Path("id") id: String, @Body task: TaskDto)

    @POST("tasks/{id}/close")
    suspend fun closeTask(@Path("id") id: String)

    @POST("tasks/{id}/reopen")
    suspend fun reopenTask(@Path("id") id: String)

    @DELETE("/tasks/{id}")
    suspend fun deleteTask(@Path("id") id: String)

    //Labels

    //Personal labels
    @GET("labels")
    suspend fun getAllPersonalLabels(): List<LabelDto>

    @POST("labels")
    suspend fun createPersonalLabel(@Body label: LabelDto)

    @GET("labels/{id}")
    suspend fun getPersonalLabelById(@Path("id") id: String)

    @POST("labels/{id}")
    suspend fun updatePersonalLabelById(@Path("id") id: String, @Body label: LabelDto)

    @DELETE("labels/{id}")
    suspend fun deleteLabelById(@Path("id") id: String)

    // Shared Labels
    @GET("labels/shared")
    suspend fun getAllSharedLabels(): List<String>

    @POST("labels/shared/rename")
    suspend fun renameSharedLabels(@Body renamedLabel: RenamedLabelDto)

    @POST("labels/shared/remove")
    suspend fun  removeSharedLabels(@Body labelName: String)


}