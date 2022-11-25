package todo.domain.usescases

import todo.domain.RepoProjects
import todo.domain.ResponseState
import todo.framework.Project
import todo.toProject
import todo.toProjectDomain
import javax.inject.Inject

class CreateProjectUC @Inject constructor (private val repoProjects: RepoProjects) {

    //Los metodos de Create Update and Delete de la Api
    //retornan un valor -1 cuando fallan. Tener en cuenta
    // para retroalimentar al cliente cuando falle la Api
   suspend  fun createProject(project: Project):ResponseState<Project?>{
       val result = repoProjects.createProjectInApi(project.toProjectDomain())
      return if(result is ResponseState.Success){
            repoProjects.createProjectInDB(result.data!!)
            val response = repoProjects.getProjectByIdFromDB(result.data.id)?.toProject()
          ResponseState.Success(response)
        }else{
            ResponseState.Error("Error to create project in server")
        }
    }
}