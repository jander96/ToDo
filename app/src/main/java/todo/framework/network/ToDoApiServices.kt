package todo.framework.network

import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import org.jetbrains.annotations.Nullable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ToDoApiServices {
    //Projects
    @GET("projects")
    fun getAllProjects(): List<ProjectDto>

    @POST("projects")
    suspend fun createProject(@Body projectDto: ProjectDto)

    @GET("projects/{id}")
    suspend fun getProjectById(@Path("id") idProject: String): ProjectDto

    @POST("projects/{id}")
    suspend fun updateProject(@Path("id") idProject: String, @Body projectDto: ProjectDto)
    @DELETE("projects/{id}")
    suspend fun deleteProject(@Path("id") idProject: String):Response<Unit>

    @GET("projects/{id}/collaborators")
     fun getAllProjectCollaborators(@Path("id") idProject: String): List<CollaboratorsDto>

    //Task
    @GET("tasks")
    suspend fun getActiveTasks(): List<TaskDto>

    @POST("tasks")
    suspend fun createNewTask(@Body task: TaskDto)

    @GET("tasks/{id}")
    suspend fun getAnActiveTaskById(@Path("id") idTask:String):TaskDto

    @POST("tasks/{id}")
    suspend fun updateTask(@Path("id") idTask: String, @Body task: TaskDto)

    @POST("tasks/{id}/close")
    suspend fun closeTask(@Path("id") idTask: String)

    @POST("tasks/{id}/reopen")
    suspend fun reopenTask(@Path("id") idTask: String)

    @DELETE("/tasks/{id}")
    suspend fun deleteTask(@Path("id") idTask: String):Response<Unit>

    //Labels

    //Personal labels
    @GET("labels")
     fun getAllPersonalLabels(): List<LabelDto>

    @POST("labels")
    suspend fun createPersonalLabel(@Body label: LabelDto)

    @GET("labels/{id}")
    suspend fun getPersonalLabelById(@Path("id") idLabel : String):LabelDto

    @POST("labels/{id}")
    suspend fun updatePersonalLabelById(@Path("id") idLabel: String, @Body label: LabelDto)

    @DELETE("labels/{id}")
    suspend fun deleteLabelById(@Path("id") idLabel: String):Response<Unit>

    // Shared Labels
    @GET("labels/shared")
    fun getAllSharedLabels(): List<String>

    @POST("labels/shared/rename")
    suspend fun renameSharedLabels(@Body renamedLabel: RenamedLabelDto)

    @POST("labels/shared/remove")
    suspend fun  removeSharedLabels(@Body labelName: String)


}