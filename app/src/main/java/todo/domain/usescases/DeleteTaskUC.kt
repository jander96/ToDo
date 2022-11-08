package todo.domain.usescases

import todo.domain.Repo

class DeleteTaskUC(private val repo:Repo) {
    suspend fun deleteTask(idTask: String){
        repo.deleteTask(idTask)
    }
}