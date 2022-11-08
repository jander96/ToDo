package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Task
import todo.toTaskDomain

class CreateNewTaskUC(private val repo: Repo) {
    suspend fun creteNewTask(task: Task){
        repo.createNewTask(task.toTaskDomain())
    }
}