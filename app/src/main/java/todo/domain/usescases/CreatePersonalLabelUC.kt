package todo.domain.usescases


import todo.domain.RepoLabels
import todo.framework.Label
import todo.toLabel
import todo.toLabelDomain
import todo.toTaskDomain
import javax.inject.Inject

class CreatePersonalLabelUC @Inject constructor (private val repoLabels: RepoLabels) {

    //Los metodos de Create Update and Delete de la Api
    //retornan un valor -1 cuando fallan. Tener en cuenta
    // para retroalimentar al cliente cuando falle la Api
    suspend fun  createPersonalLabel(label: Label):Label?{
        val result = repoLabels.createPersonalLabelInApi(label.toLabelDomain())
        return if(result !=null){
            repoLabels.createPersonalLabelInDB(result)
            repoLabels.getPersonalLabelByIdFromDB(result.id).toLabel()
        }else{
            null
        }

    }
}