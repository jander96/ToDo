package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Label
import todo.toLabelDomain

class UpdatePersonalLabelByIdUC(private val repo:Repo) {
    suspend fun  updatePersonalLabelById(label:Label){
        repo.updatePersonalLabelById(label.id,label.toLabelDomain())
    }
}