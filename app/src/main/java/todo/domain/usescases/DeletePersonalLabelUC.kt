package todo.domain.usescases

import todo.domain.RepoLabels
import javax.inject.Inject

class DeletePersonalLabelUC @Inject constructor (private val repoLabels: RepoLabels){
    suspend fun deletePersonalLabel(idLabel:String):Int{
        val result = repoLabels.deleteLabelByIdInApi(idLabel)
       return if(result !=-1){
            repoLabels.deleteLabelByIdInDB(idLabel)
           result
        }else{
            -1
        }
    }
}