package todo.domain.usescases

import todo.domain.RepoTask
import todo.domain.ResponseState
import todo.framework.Task
import todo.toTask
import todo.toTaskDomain
import javax.inject.Inject

class UpdateTaskUC @Inject constructor(private val repoTask: RepoTask) {
    suspend fun updateTask(task: Task): ResponseState<Task?> {
        val result = repoTask.updateTaskInApi(task.id, task.toTaskDomain())

        return if (result is ResponseState.Success) {
            repoTask.updateTaskInDB(result.data!!.id, result.data)
            val response = repoTask.getAnActiveTaskByIdFromDB(result.data.id).toTask()
            ResponseState.Success(response)
        } else {
            ResponseState.Error(result.messange)
        }
    }
}