package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.Repo
import todo.framework.Task
import todo.toTask
import javax.inject.Inject

class GetActiveTaskUC  (private val repo: Repo) {
   suspend fun getActiveTask(): List<Task> {
       return repo.getActiveTasks().map {
           it.toTask()
       }
    }
}