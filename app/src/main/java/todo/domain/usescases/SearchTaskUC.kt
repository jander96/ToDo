package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.RepoTask
import todo.domain.ResponseState
import todo.framework.Task
import todo.toTask
import javax.inject.Inject

class SearchTaskUC @Inject constructor (private val taskRepoTask: RepoTask) {

    fun searchTask(query:String): ResponseState<Flow<List<Task>>>{
      val response = taskRepoTask.searchTask(query).map { list->
          list.map { it.toTask() }
      }
       return ResponseState.Success(response)
    }
}