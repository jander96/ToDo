package todo.domain.usescases

import android.util.Log
import todo.domain.RepoTask
import todo.domain.ResponseState
import todo.domain.TaskDomain
import todo.framework.Task
import todo.toTask
import todo.toTaskDomain
import javax.inject.Inject

class CreateNewTaskUC @Inject constructor (private val repoTask: RepoTask) {


    /*TODO
    *  Ojito cuidado con complicarte. La API me devuelve en los metodos create
    * and update el objeto creado en la peticion. Si añado este objeto de retorno
    * a los metodos de la Api entonces podre obtener aqui en los casos de uso una
    * variable que contenga el objeto y lo paso directo a la base de datos */

    suspend fun creteNewTask(task: Task): ResponseState<Task?> {
        val result = repoTask.createNewTaskInApi(task.toTaskDomain())
        return if(result is ResponseState.Success){
            repoTask.createNewTaskInDB(result.data!!)
            val response = repoTask.getAnActiveTaskByIdFromDB(result.data.id).toTask()
            Log.d("Task","Se creo la tarea")
            ResponseState.Success(response)
        }else{
            ResponseState.Error("Error to create Task in server")
        }
    }
}