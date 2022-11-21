package todo.domain.usescases

import todo.domain.RepoLabels
import todo.framework.Label
import todo.toLabel
import javax.inject.Inject

class GetPersonalLabelByIdUC @Inject constructor (private val repoLabels: RepoLabels) {
    suspend fun getPersonalLabelById(idLabel:String): Label? {
        val result = repoLabels.getPersonalLabelByIdFromApi(idLabel)

        return if(result!=null){
           repoLabels.createPersonalLabelInDB(result)
           repoLabels.getPersonalLabelByIdFromDB(idLabel).toLabel()
        }else{
            null
        }
    }
}
/* En el caso de los get by Id cuando añado el objeto a la base de datos no los borro primero
* ya que Room identific si se trata del mismo objeto usando el id y en lugar de añadirlo lo actualiza*/