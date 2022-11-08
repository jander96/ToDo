package todo.domain.usescases

import todo.domain.Repo

class RemoveSharedLabelUC(private val repo:Repo) {
    suspend fun removeSharedLabel(labelName:String){
        repo.removeSharedLabels(labelName)
    }
}