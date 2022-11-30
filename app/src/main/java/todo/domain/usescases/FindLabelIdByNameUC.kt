package todo.domain.usescases

import todo.domain.RepoLabels
import javax.inject.Inject

class FindLabelIdByNameUC @Inject constructor (private val repoLabels: RepoLabels) {

    suspend fun findLabelIdByName(labelName: String): String{
       return  repoLabels.findLabelByName(labelName)
    }
}