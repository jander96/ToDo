package todo.domain.usescases

import todo.domain.RepoProjects
import todo.framework.Project
import todo.toProject
import todo.toProjectDomain
import javax.inject.Inject

class UpdateProjectUC @Inject constructor (private val repoProjects: RepoProjects) {
    suspend fun updateProject(project: Project):Project?{
        val result = repoProjects.updateProjectInApi(project.id,project.toProjectDomain())

       return if(result != null){
            repoProjects.updateProjectInDB(result.id,result)
            repoProjects.getProjectByIdFromDB(result.id)?.toProject()
        }else{
            null
        }
    }
}