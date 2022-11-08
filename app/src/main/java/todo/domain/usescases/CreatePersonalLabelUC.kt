package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Label
import todo.toLabelDomain

class CreatePersonalLabelUC(private val repo: Repo) {
    suspend fun  createPersonalLabel(label: Label){
        repo.createPersonalLabel(label.toLabelDomain())
    }
}