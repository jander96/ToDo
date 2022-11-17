package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.Repo
import todo.framework.Label
import todo.toLabel

class GetAllPersonalLabelsUC(private val repo: Repo) {
    fun getAllPersonalLabels(): Flow<List<Label>> {
       return repo.getAllPersonalLabels().map{list->
          list.map{ it.toLabel()}
       }
    }
}