package todo.domain.usescases


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import todo.domain.RepoLabels
import todo.framework.Label
import todo.toLabel
import javax.inject.Inject

class GetAllPersonalLabelsUC @Inject constructor(private val repoLabels: RepoLabels) {
    suspend fun getAllPersonalLabels(): Flow<List<Label>> {
        val result = repoLabels.getAllPersonalLabelsFromApi()

        return if (result.isNotEmpty()) {
            repoLabels.clearAllLabels()
            result.forEach { repoLabels.createPersonalLabelInDB(it) }
            repoLabels.getAllPersonalLabelsFromDB().map {list->
                list.map { it.toLabel() }
            }
        } else {
            flowOf(emptyList())
        }

    }
}