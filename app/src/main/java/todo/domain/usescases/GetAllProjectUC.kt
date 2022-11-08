package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Project
import todo.toProject

class GetAllProjectUC(private val repo: Repo) {
    suspend fun getAllProjects():List<Project>{
       return repo.getAllProjects().map {
           it.toProject()
       }
    }
}