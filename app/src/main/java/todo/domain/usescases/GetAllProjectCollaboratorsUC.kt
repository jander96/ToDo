package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.Repo
import todo.framework.Collaborators
import todo.toCollaborators

class GetAllProjectCollaboratorsUC(private val repo: Repo) {
    fun getAllProjectCollaborators(idProject:String): Flow<List<Collaborators>> {
       return repo.getAllProjectCollaborators(idProject).map {list->
          list.map{ it.toCollaborators()}
       }
    }
} 