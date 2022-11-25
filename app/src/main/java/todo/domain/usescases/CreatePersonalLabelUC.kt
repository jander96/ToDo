package todo.domain.usescases


import todo.domain.RepoLabels
import todo.domain.ResponseState
import todo.framework.Label
import todo.toLabel
import todo.toLabelDomain
import todo.toTaskDomain
import javax.inject.Inject

class CreatePersonalLabelUC @Inject constructor (private val repoLabels: RepoLabels) {

    //Los metodos de Create Update and Delete de la Api
    //retornan un valor -1 cuando fallan. Tener en cuenta
    // para retroalimentar al cliente cuando falle la Api
    suspend fun  createPersonalLabel(label: Label):ResponseState<Label?>{
        val result = repoLabels.createPersonalLabelInApi(label.toLabelDomain())
        return if(result is ResponseState.Success){
            repoLabels.createPersonalLabelInDB(result.data!!)
            val response = repoLabels.getPersonalLabelByIdFromDB(result.data.id).toLabel()
            ResponseState.Success(response)
        }else{
            ResponseState.Error("Error to create label in Server ")
        }

    }
}