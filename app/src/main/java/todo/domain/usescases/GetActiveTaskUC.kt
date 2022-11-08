package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Task
import todo.toTask

class GetActiveTaskUC(private val repo: Repo) {
    suspend fun getActiveTask(): List<Task>{
       return repo.getActiveTasks().map {
           it.toTask()
       }
    }
}