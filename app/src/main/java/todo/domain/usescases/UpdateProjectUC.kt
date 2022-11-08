package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Project
import todo.toProjectDomain

class UpdateProjectUC(private val repo: Repo) {
    suspend fun updateProject(project: Project){
        repo.updateProject(project.id,project.toProjectDomain())
    }
}