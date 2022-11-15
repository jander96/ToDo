package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Collaborators
import todo.toCollaborators

class GetAllProjectCollaboratorsUC(private val repo: Repo) {
    suspend fun getAllProjectCollaborators(idProject:String):List<Collaborators>{
       return repo.getAllProjectCollaborators(idProject).map {
           it.toCollaborators()
       }
    }
} 