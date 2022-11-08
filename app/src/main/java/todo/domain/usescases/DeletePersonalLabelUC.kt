package todo.domain.usescases

import todo.domain.Repo

class DeletePersonalLabelUC(private val repo:Repo) {
    suspend fun deletePersonalLabel(idLabel:String){
        repo.deleteLabelById(idLabel)
    }
}