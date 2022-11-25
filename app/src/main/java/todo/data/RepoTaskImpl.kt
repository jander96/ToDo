package todo.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import todo.domain.LocalResource
import todo.domain.NetworkResources
import todo.domain.RepoTask
import todo.domain.ResponseState
import todo.domain.TaskDomain
import todo.toDomain
import javax.inject.Inject

class RepoTaskImpl
@Inject constructor(
    private val localResource: LocalResource,
    private val networkResources: NetworkResources
) : RepoTask {
    override suspend fun getActiveTasksFromApi(): ResponseState<List<TaskDomain>> {
       return networkResources.getActiveTasks()
    }

    override fun searchTask(query: String): Flow<List<TaskDomain>> {
        return localResource.searchTask(query).map{list->
        list.map { it.toDomain() }
        }

    }

    override fun getActiveTasksFromDB(): Flow<List<TaskDomain>> {
        return localResource.getActiveTasks()
    }

    override suspend fun createNewTaskInApi(task: TaskDomain): ResponseState<TaskDomain?> {
        return networkResources.createNewTask(task)
    }

    override suspend fun createNewTaskInDB(task: TaskDomain) {
       localResource.createNewTask(task)
    }

    override suspend fun getAnActiveTaskByIdFromApi(idTask: String): ResponseState<TaskDomain?> {
        return networkResources.getAnActiveTaskById(idTask)
    }

    override suspend fun getAnActiveTaskByIdFromDB(idTask: String): TaskDomain {
        return localResource.getAnActiveTaskById(idTask)
    }

    override suspend fun updateTaskInApi(idTask: String, task: TaskDomain): ResponseState<TaskDomain?> {
       return networkResources.updateTask(idTask,task)
    }

    override suspend fun updateTaskInDB(idTask: String, task: TaskDomain) {
        localResource.updateTask(idTask,task)
    }

    override suspend fun deleteTaskInApi(idTask: String): ResponseState<Int> {
      return networkResources.deleteTask(idTask)
    }

    override suspend fun deleteTaskInDB(idTask: String){
        localResource.deleteTask(idTask)
    }

    override suspend fun clearAllTaskInDB() {
        localResource.clearAllTaskInDB()
    }
}