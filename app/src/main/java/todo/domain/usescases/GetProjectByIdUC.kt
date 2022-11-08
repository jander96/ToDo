package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Project
import todo.toProject

class GetProjectByIdUC(private val repo: Repo) {
    suspend fun getProjectById(idProject: String):Project{
        return repo.getProjectById(idProject).toProject()
    }
}