package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.RepoProjects
import todo.domain.ResponseState
import todo.framework.Project
import todo.toProject
import javax.inject.Inject

class GetAllProjectsFromDBUC @Inject constructor (private val repoProjects: RepoProjects) {
    fun getAllProjectsFromDb(): ResponseState<Flow<List<Project>>> {
        val response = repoProjects.getAllProjectsFromDB().map {list->
            list.map {
                it.toProject()
            }

        }
        return ResponseState.Success(response)

    }
}