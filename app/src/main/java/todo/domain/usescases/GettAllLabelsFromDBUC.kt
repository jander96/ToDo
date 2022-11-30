package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.RepoTask
import todo.domain.ResponseState
import todo.framework.Task
import todo.toTask
import javax.inject.Inject

class GettAllTaskFromDBUC @Inject constructor( private val repoTask: RepoTask) {
    fun getAllLabelsDb(): ResponseState<Flow<List<Task>>>{
        return ResponseState.Success(
            repoTask.getActiveTasksFromDB().map {list->
            list.map { it.toTask() }
            }
        )
    }
}