package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import todo.domain.Repo
import javax.inject.Inject

class GetAllSharedLabelUC (private val repo:Repo) {
   suspend  fun getAllSharedLabels(): List<String> {
        return repo.getAllSharedLabels()
    }
}