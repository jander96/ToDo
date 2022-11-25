package todo.domain.usescases

import todo.domain.RepoProjects
import todo.domain.ResponseState
import javax.inject.Inject

class DeleteProjectUC @Inject constructor(private val  repoProjects: RepoProjects) {
    suspend fun deleteProject(idProject : String):ResponseState<Int>{
        val result = repoProjects.deleteProjectInApi(idProject)
        return if(result is ResponseState.Success){
            repoProjects.deleteProjectInDB(idProject)
            result
        }else{
            result
        }
    }
}