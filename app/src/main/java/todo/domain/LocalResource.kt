package todo.domain

import kotlinx.coroutines.flow.Flow

interface LocalResource {
    fun getAllProjects(): Flow<List<ProjectDomain>>


    suspend fun createProject( project: ProjectDomain)


    suspend fun getProjectById( idProject: String): ProjectDomain


    suspend fun updateProject( idProject: String, project: ProjectDomain)

    suspend fun deleteProject(idProject: String)


    //Task

    fun getActiveTasks(): Flow<List<TaskDomain>>


    suspend fun createNewTask(task: TaskDomain)


    suspend fun getAnActiveTaskById(idTask :String): TaskDomain


    suspend fun updateTask( idTask: String, task: TaskDomain)


    suspend fun deleteTask( idTask: String)

    //Labels

    //Personal labels

    fun getAllPersonalLabels(): Flow<List<LabelDomain>>


    suspend fun createPersonalLabel( label: LabelDomain)


    suspend fun getPersonalLabelById( idLabel: String):LabelDomain


    suspend fun updatePersonalLabelById( idLabel: String, label: LabelDomain)


    suspend fun deleteLabelById( idLabel: String)

    suspend fun clearAllProjectInDB()

    suspend fun clearAllTaskInDB()

    suspend fun clearAllLabelsInDB()





}