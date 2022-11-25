package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import todo.domain.RepoProjects
import todo.domain.ResponseState
import todo.framework.Project
import todo.toProject
import javax.inject.Inject

class GetAllProjectUC @Inject constructor (private val repoProjects: RepoProjects) {
    suspend fun getAllProjects(): ResponseState<Flow<List<Project>>> {
        val result = repoProjects.getAllProjectsFromApi()

        return if(result is ResponseState.Success){
            repoProjects.clearAllProjectsInDb()
            result.data?.forEach {repoProjects.createProjectInDB(it)}
            val response =  repoProjects.getAllProjectsFromDB().map{list->
                list.map { it.toProject() }
            }
            ResponseState.Success(response)
        }else{
            ResponseState.Error(result.messange)
        }

    }
}