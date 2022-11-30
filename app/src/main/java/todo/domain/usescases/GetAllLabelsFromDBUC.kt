package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.RepoLabels
import todo.domain.ResponseState
import todo.framework.Label
import todo.toLabel
import javax.inject.Inject

class GetAllLabelsFromDBUC @Inject constructor (private val repoLabels: RepoLabels) {
    fun getAllLabelsFromDb(): ResponseState<Flow<List<Label>>>{
        val response = repoLabels.getAllPersonalLabelsFromDB().map {list->
        list.map {
            it.toLabel()
        }

        }
         return ResponseState.Success(response)

    }
}