package todo.domain.usescases

import todo.domain.Repo
import todo.framework.Project
import todo.toProjectDomain

class CreateProjectUC(private val repo: Repo) {

   suspend  fun createProject(project: Project){
        repo.createProject(project.toProjectDomain())
    }
}