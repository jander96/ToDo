package todo.domain.usescases


import todo.domain.Repo
import todo.framework.Label
import todo.toLabel
import javax.inject.Inject

class GetAllPersonalLabelsUC  (private val repo: Repo) {
   suspend fun getAllPersonalLabels(): List<Label> {
       return repo.getAllPersonalLabels().map{
           it.toLabel()
       }
    }
}