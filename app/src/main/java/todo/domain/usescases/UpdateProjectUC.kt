package todo.domain.usescases

import todo.domain.RepoProjects
import todo.domain.ResponseState
import todo.framework.Project
import todo.toProject
import todo.toProjectDomain
import javax.inject.Inject

class UpdateProjectUC @Inject constructor (private val repoProjects: RepoProjects) {
    suspend fun updateProject(project: Project):ResponseState<Project?>{
        val result = repoProjects.updateProjectInApi(project.id,project.toProjectDomain())

       return if(result is ResponseState.Success){
            repoProjects.updateProjectInDB(result.data!!.id,result.data)
           val response = repoProjects.getProjectByIdFromDB(result.data.id)?.toProject()
           ResponseState.Success(response)
        }else{
            ResponseState.Error(result.messange)
        }
    }
}