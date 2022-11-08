package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Task
import todo.toTaskDomain

class UpdateTaskUC(private val repo: Repo) {
    suspend fun updateTask(task : Task){
        repo.updateTask(task.id,task.toTaskDomain())
    }
}