package todo.domain.usescases

import todo.domain.RepoTask
import todo.domain.ResponseState
import javax.inject.Inject

class DeleteTaskUC @Inject constructor (private val repoTask: RepoTask) {
    suspend fun deleteTask(idTask: String):ResponseState<Int>{
        val result = repoTask.deleteTaskInApi(idTask)
        return if(result is ResponseState.Success){
            repoTask.deleteTaskInDB(idTask)
            result
        }else{
           result
        }
    }
}