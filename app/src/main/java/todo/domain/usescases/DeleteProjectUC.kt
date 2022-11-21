package todo.domain.usescases

import todo.domain.RepoProjects
import javax.inject.Inject

class DeleteProjectUC @Inject constructor(private val  repoProjects: RepoProjects) {
    suspend fun deleteProject(idProject : String):Int{
        val result = repoProjects.deleteProjectInApi(idProject)
        return if(result!= -1){
            repoProjects.deleteProjectInDB(idProject)
            result
        }else{
            -1
        }
    }
}