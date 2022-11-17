package todo.domain

import kotlinx.coroutines.flow.Flow



interface Repo {

    //Project
    fun getAllProjects(): Flow<List<ProjectDomain>>

    suspend fun createProject( projectDomain: ProjectDomain)

    suspend fun getProjectById( idProject: String): ProjectDomain

    suspend fun updateProject( idProject: String, projectDomain: ProjectDomain)

    suspend fun deleteProject(idProject: String)
    fun getAllProjectCollaborators( idProject: String): Flow<List<CollaboratorsDomain>>
    //Task

    fun getActiveTasks(): Flow<List<TaskDomain>>

    suspend fun createNewTask(task: TaskDomain)

    suspend fun getAnActiveTaskById(idTask:String):TaskDomain

    suspend fun updateTask( idTask: String, task: TaskDomain)

    suspend fun closeTask( idTask: String)

    suspend fun reopenTask( idTask: String)

    suspend fun deleteTask( idTask: String)

    //Labels

    //Personal labels
    fun getAllPersonalLabels(): Flow<List<LabelDomain>>

    suspend fun createPersonalLabel( label: LabelDomain)

    suspend fun getPersonalLabelById( idLabel: String): LabelDomain

    suspend fun updatePersonalLabelById( idLabel: String, label: LabelDomain)

    suspend fun deleteLabelById( idLabel: String)

    // Shared Labels
    fun getAllSharedLabels(): Flow<List<String>>

    suspend fun renameSharedLabels( renamedLabelDomain: RenamedLabelDomain)

    suspend fun  removeSharedLabels( labelName: String)
}