package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.Repo
import todo.framework.Project
import todo.toProject

class GetAllProjectUC(private val repo: Repo) {
    fun getAllProjects(): Flow<List<Project>> {
       return repo.getAllProjects().map {list->
           list.map{it.toProject()}
       }
    }
}