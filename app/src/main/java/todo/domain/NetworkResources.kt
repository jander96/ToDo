package todo.domain


interface NetworkResources {

    //Project
    suspend fun getAllProjects(): List<ProjectDomain>


    suspend fun createProject( projectDto: ProjectDomain):ProjectDomain?


    suspend fun getProjectById( idProject: String): ProjectDomain?


    suspend fun updateProject( idProject: String, projectDto: ProjectDomain):ProjectDomain?

    suspend fun deleteProject(idProject: String):Int


    //Task

    suspend fun getActiveTasks(): List<TaskDomain>


    suspend fun createNewTask(task: TaskDomain):TaskDomain?


    suspend fun getAnActiveTaskById(idTask :String): TaskDomain?


    suspend fun updateTask( idTask: String, task: TaskDomain):TaskDomain?


    suspend fun deleteTask( idTask: String):Int

    //Labels

    //Personal labels

   suspend fun getAllPersonalLabels(): List<LabelDomain>


    suspend fun createPersonalLabel( label: LabelDomain):LabelDomain?


    suspend fun getPersonalLabelById( idLabel: String):LabelDomain?


    suspend fun updatePersonalLabelById( idLabel: String, label: LabelDomain):LabelDomain?


    suspend fun deleteLabelById( idLabel: String):Int

}