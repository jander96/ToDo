package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Label
import todo.toLabel
import javax.inject.Inject

class GetPersonalLabelByIdUC (private val repo: Repo) {
    suspend fun getPersonalLabelById(idLabel:String): Label {
        return repo.getPersonalLabelById(idLabel).toLabel()
    }
}