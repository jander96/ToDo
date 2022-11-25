package todo.domain

import kotlinx.coroutines.flow.Flow
import todo.framework.room.entities.TaskEntity

interface RepoTask {

    suspend fun getActiveTasksFromApi(): ResponseState<List<TaskDomain>>
    fun searchTask(query:String): Flow<List<TaskDomain>>
     fun getActiveTasksFromDB(): Flow<List<TaskDomain>>


    suspend fun createNewTaskInApi(task: TaskDomain):ResponseState<TaskDomain?>
    suspend fun createNewTaskInDB(task: TaskDomain)

    suspend fun getAnActiveTaskByIdFromApi(idTask:String):ResponseState<TaskDomain?>
    suspend fun getAnActiveTaskByIdFromDB(idTask:String): TaskDomain

    suspend fun updateTaskInApi( idTask: String, task: TaskDomain):ResponseState<TaskDomain?>
    suspend fun updateTaskInDB( idTask: String, task: TaskDomain)


    suspend fun deleteTaskInApi( idTask: String):ResponseState<Int>
    suspend fun deleteTaskInDB( idTask: String)
    suspend fun clearAllTaskInDB()
}