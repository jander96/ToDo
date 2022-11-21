package todo.domain.usescases

import todo.domain.RepoTask
import javax.inject.Inject

class DeleteTaskUC @Inject constructor (private val repoTask: RepoTask) {
    suspend fun deleteTask(idTask: String):Int{
        val result = repoTask.deleteTaskInApi(idTask)
        return if(result != -1){
            repoTask.deleteTaskInDB(idTask)
            result
        }else{
            -1
        }
    }
}