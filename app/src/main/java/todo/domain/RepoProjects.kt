package todo.domain

import kotlinx.coroutines.flow.Flow

interface RepoProjects {

    //Project
    suspend fun getAllProjectsFromApi(): List<ProjectDomain>
    fun getAllProjectsFromDB(): Flow<List<ProjectDomain>>

    suspend fun createProjectInApi( projectDomain: ProjectDomain):ProjectDomain?
    suspend fun createProjectInDB( projectDomain: ProjectDomain)

    suspend fun getProjectByIdFromApi( idProject: String): ProjectDomain?
    suspend fun getProjectByIdFromDB( idProject: String): ProjectDomain?

    suspend fun updateProjectInApi( idProject: String, projectDomain: ProjectDomain):ProjectDomain?
    suspend fun updateProjectInDB( idProject: String, projectDomain: ProjectDomain)

    suspend fun deleteProjectInApi(idProject: String):Int
    suspend fun deleteProjectInDB(idProject: String)
    suspend fun clearAllProjectsInDb()
}