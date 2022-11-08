package todo.domain.usescases

import todo.domain.Repo

class GetAllSharedLabelUC(private val repo:Repo) {
    suspend fun getAllSharedLabels():List<String>{
        return repo.getAllSharedLabels()
    }
}