package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Task
import todo.toTask

class GetActiveTaskByIdUC(private val repo: Repo) {
    suspend fun getActivwTaskById(idTask : String):Task{
         return repo.getAnActiveTaskById(idTask).toTask()
     }
}