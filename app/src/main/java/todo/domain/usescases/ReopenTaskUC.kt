package todo.domain.usescases

import todo.domain.Repo

class ReopenTaskUC(private val repo:Repo){
    suspend fun reopenTask(idTask: String){
        repo.reopenTask(idTask)
    }
}