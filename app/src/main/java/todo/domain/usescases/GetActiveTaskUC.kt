package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.Repo
import todo.framework.Task
import todo.toTask

class GetActiveTaskUC(private val repo: Repo) {
    fun getActiveTask(): Flow<List<Task>> {
       return repo.getActiveTasks().map {list->
          list.map{ it.toTask()}
       }
    }
}