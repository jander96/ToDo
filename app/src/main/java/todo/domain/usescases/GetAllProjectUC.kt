package todo.domain.usescases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import todo.domain.RepoProjects
import todo.framework.Project
import todo.toProject
import javax.inject.Inject

class GetAllProjectUC @Inject constructor (private val repoProjects: RepoProjects) {
    suspend fun getAllProjects(): Flow<List<Project>> {
        val result = repoProjects.getAllProjectsFromApi()

        return if(result.isNotEmpty()){
            repoProjects.clearAllProjectsInDb()
            result.forEach {repoProjects.createProjectInDB(it)}
            repoProjects.getAllProjectsFromDB().map{list->
                list.map { it.toProject() }
            }
        }else{
            flowOf(emptyList())
        }

    }
}