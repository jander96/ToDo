package todo.domain.usescases

import todo.domain.RepoProjects
import javax.inject.Inject

class FindProjectByNameUC @Inject constructor( private val repoProjects: RepoProjects) {

    suspend fun findProjectIdByName(projectName: String): String{
        return repoProjects.findProjectIdByName(projectName)
    }
}