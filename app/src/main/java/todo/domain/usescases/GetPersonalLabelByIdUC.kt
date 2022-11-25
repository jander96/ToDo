package todo.domain.usescases

import todo.domain.RepoLabels
import todo.domain.ResponseState
import todo.framework.Label
import todo.toLabel
import javax.inject.Inject

class GetPersonalLabelByIdUC @Inject constructor (private val repoLabels: RepoLabels) {
    suspend fun getPersonalLabelById(idLabel:String): ResponseState<Label?> {
        val result = repoLabels.getPersonalLabelByIdFromApi(idLabel)

        return if(result is ResponseState.Success){
           repoLabels.createPersonalLabelInDB(result.data!!)
           val response =repoLabels.getPersonalLabelByIdFromDB(idLabel).toLabel()
            ResponseState.Success(response)
        }else{
            ResponseState.Error(result.messange)
        }
    }
}
/* En el caso de los get by Id cuando añado el objeto a la base de datos no los borro primero
* ya que Room identific si se trata del mismo objeto usando el id y en lugar de añadirlo lo actualiza*/