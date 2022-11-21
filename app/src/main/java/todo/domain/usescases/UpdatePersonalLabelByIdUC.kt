package todo.domain.usescases

import todo.domain.RepoLabels
import todo.framework.Label
import todo.toLabel
import todo.toLabelDomain
import javax.inject.Inject

class UpdatePersonalLabelByIdUC @Inject constructor (private val repoLabels: RepoLabels) {
    suspend fun  updatePersonalLabelById(label:Label):Label?{
        val result = repoLabels.updatePersonalLabelByIdInApi(label.id,label.toLabelDomain())

        return if(result != null){
            repoLabels.updatePersonalLabelByIdInDB(result.id,result)
            repoLabels.getPersonalLabelByIdFromDB(result.id).toLabel()
        }else{
            null
        }

    }
}