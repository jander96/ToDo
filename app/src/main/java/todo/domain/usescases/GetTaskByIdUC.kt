package todo.domain.usescases

import todo.domain.RepoTask
import todo.domain.ResponseState
import todo.framework.Task
import todo.toTask
import javax.inject.Inject

class GetTaskByIdUC @Inject constructor  (private val repoTask: RepoTask) {
    suspend fun getActiveTaskById(idTask : String):ResponseState<Task?>{
        val result = repoTask.getAnActiveTaskByIdFromApi(idTask)

        return if(result is ResponseState.Success){
            repoTask.createNewTaskInDB(result.data!!)
            val response = repoTask.getAnActiveTaskByIdFromDB(idTask).toTask()
            ResponseState.Success(response)
        }else{
           ResponseState.Error(result.messange)
        }

     }
}