package todo.domain.usescases

import todo.domain.RepoProjects
import todo.framework.Project
import todo.toProject
import javax.inject.Inject

class GetProjectByIdUC @Inject constructor (private val repoProjects: RepoProjects) {
    suspend fun getProjectById(idProject: String):Project?{
        val result = repoProjects.getProjectByIdFromApi(idProject)

        return if(result!= null){
            repoProjects.createProjectInDB(result)
            repoProjects.getProjectByIdFromDB(idProject)?.toProject()
        }else{
            null
        }
    }
}