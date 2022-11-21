package todo.domain.usescases

import todo.domain.RepoProjects
import todo.framework.Project
import todo.toProject
import todo.toProjectDomain
import javax.inject.Inject

class CreateProjectUC @Inject constructor (private val repoProjects: RepoProjects) {

    //Los metodos de Create Update and Delete de la Api
    //retornan un valor -1 cuando fallan. Tener en cuenta
    // para retroalimentar al cliente cuando falle la Api
   suspend  fun createProject(project: Project):Project?{
       val result = repoProjects.createProjectInApi(project.toProjectDomain())?.toProject()
      return if(result != null){
            repoProjects.createProjectInDB(result.toProjectDomain())
            repoProjects.getProjectByIdFromDB(result.id)?.toProject()
        }else{
            null
        }
    }
}