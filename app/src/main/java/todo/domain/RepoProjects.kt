package todo.domain

import kotlinx.coroutines.flow.Flow

interface RepoProjects {

    //Project
    suspend fun getAllProjectsFromApi(): ResponseState<List<ProjectDomain>>
    fun getAllProjectsFromDB(): Flow<List<ProjectDomain>>
    suspend fun findProjectIdByName(projectName: String): String

    suspend fun createProjectInApi( projectDomain: ProjectDomain):ResponseState<ProjectDomain?>
    suspend fun createProjectInDB( projectDomain: ProjectDomain)

    suspend fun getProjectByIdFromApi( idProject: String):ResponseState< ProjectDomain?>
    suspend fun getProjectByIdFromDB( idProject: String): ProjectDomain?

    suspend fun updateProjectInApi( idProject: String, projectDomain: ProjectDomain):ResponseState<ProjectDomain?>
    suspend fun updateProjectInDB( idProject: String, projectDomain: ProjectDomain)

    suspend fun deleteProjectInApi(idProject: String):ResponseState<Int>
    suspend fun deleteProjectInDB(idProject: String)
    suspend fun clearAllProjectsInDb()

}