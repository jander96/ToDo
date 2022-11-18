package todo.domain.usescases

import todo.domain.Repo
import javax.inject.Inject

class RemoveSharedLabelUC (private val repo:Repo) {
    suspend fun removeSharedLabel(labelName:String){
        repo.removeSharedLabels(labelName)
    }
}