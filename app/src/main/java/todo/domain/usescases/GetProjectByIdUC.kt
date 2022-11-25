package todo.domain.usescases

import todo.domain.RepoProjects
import todo.domain.ResponseState
import todo.framework.Project
import todo.toProject
import javax.inject.Inject

class GetProjectByIdUC @Inject constructor (private val repoProjects: RepoProjects) {
    suspend fun getProjectById(idProject: String):ResponseState<Project?>{
        val result = repoProjects.getProjectByIdFromApi(idProject)

        return if(result is ResponseState.Success){
            repoProjects.createProjectInDB(result.data!!)
            val response = repoProjects.getProjectByIdFromDB(idProject)?.toProject()
            ResponseState.Success(response)
        }else{
            ResponseState.Error(result.messange)
        }
    }
}