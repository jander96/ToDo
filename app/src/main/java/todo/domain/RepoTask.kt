package todo.domain

import kotlinx.coroutines.flow.Flow

interface RepoTask {

    suspend fun getActiveTasksFromApi(): List<TaskDomain>
     fun getActiveTasksFromDB(): Flow<List<TaskDomain>>

    suspend fun createNewTaskInApi(task: TaskDomain):TaskDomain?
    suspend fun createNewTaskInDB(task: TaskDomain)

    suspend fun getAnActiveTaskByIdFromApi(idTask:String):TaskDomain?
    suspend fun getAnActiveTaskByIdFromDB(idTask:String):TaskDomain

    suspend fun updateTaskInApi( idTask: String, task: TaskDomain):TaskDomain?
    suspend fun updateTaskInDB( idTask: String, task: TaskDomain)


    suspend fun deleteTaskInApi( idTask: String):Int
    suspend fun deleteTaskInDB( idTask: String)
    suspend fun clearAllTaskInDB()
}