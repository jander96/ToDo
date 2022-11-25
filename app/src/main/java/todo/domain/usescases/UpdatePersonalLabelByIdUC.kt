package todo.domain.usescases

import todo.domain.RepoLabels
import todo.domain.ResponseState
import todo.framework.Label
import todo.toLabel
import todo.toLabelDomain
import javax.inject.Inject

class UpdatePersonalLabelByIdUC @Inject constructor (private val repoLabels: RepoLabels) {
    suspend fun  updatePersonalLabelById(label:Label):ResponseState<Label?>{
        val result = repoLabels.updatePersonalLabelByIdInApi(label.id,label.toLabelDomain())

        return if(result is ResponseState.Success){
            repoLabels.updatePersonalLabelByIdInDB(result.data!!.id,result.data)
           val response = repoLabels.getPersonalLabelByIdFromDB(result.data.id).toLabel()
            ResponseState.Success(response)
        }else{
            ResponseState.Error(result.messange)
        }

    }
}