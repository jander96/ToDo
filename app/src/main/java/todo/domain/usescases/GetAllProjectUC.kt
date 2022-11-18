package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.Repo
import todo.framework.Project
import todo.toProject
import javax.inject.Inject

class GetAllProjectUC (private val repo: Repo) {
    suspend fun getAllProjects(): List<Project> {
       return repo.getAllProjects().map {
          it.toProject()
       }
    }
}