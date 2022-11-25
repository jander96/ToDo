package todo.domain.usescases

import todo.domain.RepoLabels
import todo.domain.ResponseState
import javax.inject.Inject

class DeletePersonalLabelUC @Inject constructor (private val repoLabels: RepoLabels){
    suspend fun deletePersonalLabel(idLabel:String):ResponseState<Int>{
        val result = repoLabels.deleteLabelByIdInApi(idLabel)
       return if(result is ResponseState.Success){
            repoLabels.deleteLabelByIdInDB(idLabel)
           result
        }else{
            result
        }
    }
}