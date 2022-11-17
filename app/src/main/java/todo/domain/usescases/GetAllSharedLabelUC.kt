package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import todo.domain.Repo

class GetAllSharedLabelUC(private val repo:Repo) {
    fun getAllSharedLabels(): Flow<List<String>> {
        return repo.getAllSharedLabels()
    }
}