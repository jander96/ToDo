package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import todo.domain.RepoTask
import todo.domain.ResponseState
import todo.framework.Task
import todo.toTask
import javax.inject.Inject

class GetAllTaskUC @Inject constructor(private val repoTask: RepoTask) {
     suspend fun getAllTasks(): ResponseState<Flow<List<Task>>> {
        val result = repoTask.getActiveTasksFromApi() // obtengo las tareas desde la Api
        return if (result is ResponseState.Success) {                // compruebo que no es un alista vacia
            repoTask.clearAllTaskInDB()                // limpio mi base de datos antes de volver a meter toda la lista completa
            result.data?.forEach { repoTask.createNewTaskInDB(it) } // coloco la lista completa aactualizada de nuevo en la base de datos
            val response = repoTask.getActiveTasksFromDB().map { list ->
                list.map{it.toTask()}
            }
            ResponseState.Success(response)
        } else {
           ResponseState.Error(result.messange)
        }
    }
}