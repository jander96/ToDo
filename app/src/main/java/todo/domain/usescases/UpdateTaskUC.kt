package todo.domain.usescases

import todo.domain.RepoTask
import todo.framework.Task
import todo.toTask
import todo.toTaskDomain
import javax.inject.Inject

class UpdateTaskUC @Inject constructor(private val repoTask: RepoTask) {
    suspend fun updateTask(task: Task): Task? {
        val result = repoTask.updateTaskInApi(task.id, task.toTaskDomain())

        return if (result != null) {
            repoTask.updateTaskInDB(result.id, result)
            repoTask.getAnActiveTaskByIdFromDB(result.id).toTask()
        } else {
            null
        }
    }
}