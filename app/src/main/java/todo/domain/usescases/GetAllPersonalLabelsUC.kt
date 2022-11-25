package todo.domain.usescases


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import todo.domain.RepoLabels
import todo.domain.ResponseState
import todo.framework.Label
import todo.toLabel
import javax.inject.Inject

class GetAllPersonalLabelsUC @Inject constructor(private val repoLabels: RepoLabels) {
    suspend fun getAllPersonalLabels(): ResponseState<Flow<List<Label>>> {
        val result = repoLabels.getAllPersonalLabelsFromApi()

        return if (result is ResponseState.Success) {
            repoLabels.clearAllLabels()
            result.data?.forEach { repoLabels.createPersonalLabelInDB(it) }
            val response =repoLabels.getAllPersonalLabelsFromDB().map {list->
                list.map { it.toLabel() }
            }
            ResponseState.Success(response)
        } else {
            ResponseState.Error(result.messange)

        }

    }
}