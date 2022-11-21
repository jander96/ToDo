package todo.domain.usescases

import todo.domain.RepoTask
import todo.framework.Task
import todo.toTask
import javax.inject.Inject

class GetTaskByIdUC @Inject constructor  (private val repoTask: RepoTask) {
    suspend fun getActiveTaskById(idTask : String):Task?{
        val result = repoTask.getAnActiveTaskByIdFromApi(idTask)

        return if(result != null){
            repoTask.createNewTaskInDB(result)
            repoTask.getAnActiveTaskByIdFromDB(idTask).toTask()
        }else{
           null
        }

     }
}