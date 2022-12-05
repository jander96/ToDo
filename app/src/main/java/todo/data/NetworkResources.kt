package todo.data

import todo.domain.LabelDomain
import todo.domain.ProjectDomain
import todo.domain.ResponseState
import todo.domain.TaskDomain


interface NetworkResources {

    //Project
    suspend fun getAllProjects(): ResponseState<List<ProjectDomain>>


    suspend fun createProject( projectDto: ProjectDomain): ResponseState<ProjectDomain?>


    suspend fun getProjectById( idProject: String): ResponseState<ProjectDomain?>


    suspend fun updateProject( idProject: String, projectDto: ProjectDomain): ResponseState<ProjectDomain?>

    suspend fun deleteProject(idProject: String): ResponseState<Int>


    //Task

    suspend fun getActiveTasks(): ResponseState<List<TaskDomain>>


    suspend fun createNewTask(task: TaskDomain): ResponseState<TaskDomain?>


    suspend fun getAnActiveTaskById(idTask :String): ResponseState<TaskDomain?>


    suspend fun updateTask( idTask: String, task: TaskDomain): ResponseState<TaskDomain?>


    suspend fun deleteTask( idTask: String): ResponseState<Int>

    //Labels

    //Personal labels

   suspend fun getAllPersonalLabels(): ResponseState<List<LabelDomain>>


    suspend fun createPersonalLabel( label: LabelDomain): ResponseState<LabelDomain?>


    suspend fun getPersonalLabelById( idLabel: String): ResponseState<LabelDomain?>


    suspend fun updatePersonalLabelById( idLabel: String, label: LabelDomain): ResponseState<LabelDomain?>


    suspend fun deleteLabelById( idLabel: String): ResponseState<Int>

}