package todo.data

import kotlinx.coroutines.flow.Flow
import todo.domain.ProjectDomain
import todo.domain.RepoProjects
import todo.domain.ResponseState
import javax.inject.Inject

class RepoProjectsImpl
@Inject constructor(
    private val localResource: LocalResource,
    private val networkResources: NetworkResources
) : RepoProjects {
    override suspend fun getAllProjectsFromApi(): ResponseState<List<ProjectDomain>> {
       return networkResources.getAllProjects()
    }

    override  fun getAllProjectsFromDB(): Flow<List<ProjectDomain>> {
        return localResource.getAllProjects()
    }

    override suspend fun createProjectInApi(projectDomain: ProjectDomain): ResponseState<ProjectDomain?> {
      return networkResources.createProject(projectDomain)
    }

    override suspend fun createProjectInDB(projectDomain: ProjectDomain) {
       localResource.createProject(projectDomain)
    }

    override suspend fun getProjectByIdFromApi(idProject: String): ResponseState<ProjectDomain?> {
        return networkResources.getProjectById(idProject)
    }

    override suspend fun getProjectByIdFromDB(idProject: String): ProjectDomain? {
        return localResource.getProjectById(idProject)
    }

    override suspend fun findProjectIdByName(projectName: String): String {
        return localResource.findProjectIdByName(projectName)
    }

    override suspend fun updateProjectInApi(idProject: String, projectDomain: ProjectDomain): ResponseState<ProjectDomain?> {
       return networkResources.updateProject(idProject,projectDomain)
    }

    override suspend fun updateProjectInDB(idProject: String, projectDomain: ProjectDomain) {
       localResource.updateProject(idProject,projectDomain)
    }

    override suspend fun deleteProjectInApi(idProject: String): ResponseState<Int> {
       return networkResources.deleteProject(idProject)
    }

    override suspend fun clearAllProjectsInDb() {
        localResource.clearAllProjectInDB()
    }

    override suspend fun deleteProjectInDB(idProject: String) {
       localResource.deleteProject(idProject)
    }
}