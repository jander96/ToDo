package todo.data

import kotlinx.coroutines.flow.Flow
import todo.domain.LocalResource
import todo.domain.NetworkResources
import todo.domain.ProjectDomain
import todo.domain.RepoProjects
import javax.inject.Inject

class RepoProjectsImpl
@Inject constructor(
    private val localResource: LocalResource,
    private val networkResources: NetworkResources
) : RepoProjects {
    override suspend fun getAllProjectsFromApi(): List<ProjectDomain> {
       return networkResources.getAllProjects()
    }

    override  fun getAllProjectsFromDB(): Flow<List<ProjectDomain>> {
        return localResource.getAllProjects()
    }

    override suspend fun createProjectInApi(projectDomain: ProjectDomain): ProjectDomain? {
      return networkResources.createProject(projectDomain)
    }

    override suspend fun createProjectInDB(projectDomain: ProjectDomain) {
       localResource.createProject(projectDomain)
    }

    override suspend fun getProjectByIdFromApi(idProject: String): ProjectDomain? {
        return networkResources.getProjectById(idProject)
    }

    override suspend fun getProjectByIdFromDB(idProject: String): ProjectDomain {
        return localResource.getProjectById(idProject)
    }

    override suspend fun updateProjectInApi(idProject: String, projectDomain: ProjectDomain): ProjectDomain? {
       return networkResources.updateProject(idProject,projectDomain)
    }

    override suspend fun updateProjectInDB(idProject: String, projectDomain: ProjectDomain) {
       localResource.updateProject(idProject,projectDomain)
    }

    override suspend fun deleteProjectInApi(idProject: String):Int{
       return networkResources.deleteProject(idProject)
    }

    override suspend fun clearAllProjectsInDb() {
        localResource.clearAllProjectInDB()
    }

    override suspend fun deleteProjectInDB(idProject: String) {
       localResource.deleteProject(idProject)
    }
}