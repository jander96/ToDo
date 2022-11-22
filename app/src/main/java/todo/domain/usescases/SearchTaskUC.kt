package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.RepoTask
import todo.framework.Task
import todo.toTask
import javax.inject.Inject

class SearchTaskUC @Inject constructor (private val taskRepoTask: RepoTask) {

    fun searchTask(query:String): Flow<List<Task>>{
      return  taskRepoTask.searchTask(query).map { list->
          list.map { it.toTask() }
      }
    }
}