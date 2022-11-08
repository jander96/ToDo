package todo.domain.usescases

import todo.domain.Repo

class CloseTaskUC(private val repo: Repo) {
    suspend fun closeTask(idTask: String){
        repo.closeTask(idTask)
    }
}