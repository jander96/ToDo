package todo.domain

import kotlinx.coroutines.flow.Flow

interface NetworkResources {

    //Project
    suspend fun getAllProjects(): List<ProjectDomain>


    suspend fun createProject( projectDto: ProjectDomain)


    suspend fun getProjectById( idProject: String): ProjectDomain


    suspend fun updateProject( idProject: String, projectDto: ProjectDomain)

    suspend fun deleteProject(idProject: String)

    suspend fun getAllProjectCollaborators( idProject: String): List<CollaboratorsDomain>

    //Task

    suspend fun getActiveTasks(): List<TaskDomain>


    suspend fun createNewTask(task: TaskDomain)


    suspend fun getAnActiveTaskById(idTask :String): TaskDomain


    suspend fun updateTask( idTask: String, task: TaskDomain)


    suspend fun closeTask( idTask: String)


    suspend fun reopenTask( idTask: String)


    suspend fun deleteTask( idTask: String)

    //Labels

    //Personal labels

   suspend fun getAllPersonalLabels(): List<LabelDomain>


    suspend fun createPersonalLabel( label: LabelDomain)


    suspend fun getPersonalLabelById( idLabel: String):LabelDomain


    suspend fun updatePersonalLabelById( idLabel: String, label: LabelDomain)


    suspend fun deleteLabelById( idLabel: String)

    // Shared Labels

    suspend fun getAllSharedLabels(): List<String>


    suspend fun renameSharedLabels( renamedLabel: RenamedLabelDomain)


    suspend fun  removeSharedLabels( labelName: String)

}