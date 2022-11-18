package todo.domain.usescases

import todo.domain.Repo
import javax.inject.Inject

class ReopenTaskUC (private val repo:Repo){
    suspend fun reopenTask(idTask: String){
        repo.reopenTask(idTask)
    }
}