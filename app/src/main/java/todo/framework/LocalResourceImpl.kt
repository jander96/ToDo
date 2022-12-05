package todo.framework

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import todo.domain.LabelDomain
import todo.data.LocalResource
import todo.domain.ProjectDomain
import todo.domain.TaskDomain
import todo.framework.room.TodoDataBase
import todo.framework.room.entities.TaskEntity
import todo.toDomain
import todo.toEntity
import javax.inject.Inject

class LocalResourceImpl @Inject constructor (private val database:TodoDataBase): LocalResource {

    //Projects
    override fun getAllProjects(): Flow<List<ProjectDomain>> {
       return database.getProjectDao().getAllProjects().map {list->
           list.map { it.toDomain() }
       }

    }

    override fun searchTask(query: String): Flow<List<TaskEntity>> {
        Log.d("room","Se resliza la busqueda en Base de Datos")
       return database.getTaskDao().searchTask(query)

    }

    override suspend fun createProject(project: ProjectDomain){
         database.getProjectDao().createProject(project.toEntity())
    }

    override suspend fun getProjectById(idProject: String): ProjectDomain? {
      return database.getProjectDao().getProjectById(idProject).toDomain()

    }

    override suspend fun updateProject(idProject: String, project: ProjectDomain) {
        database.getProjectDao().updateProject(project.toEntity())
    }

    override suspend fun deleteProject(idProject: String) {
       database.getProjectDao().deleteProject(idProject)
    }


    //Task
    override  fun getActiveTasks(): Flow<List<TaskDomain>> {
       return  database.getTaskDao().getActiveTasks().map{
           it.map { it.toDomain() }
       }

    }

    override suspend fun createNewTask(task: TaskDomain) {
       database.getTaskDao().createNewTask(task.toEntity())
    }

    override suspend fun getAnActiveTaskById(idTask: String): TaskDomain {
       return database.getTaskDao().getAnActiveTaskById(idTask).toDomain()

    }

    override suspend fun findProjectIdByName(projectName: String): String {
        return database.getProjectDao().findProjectIdByName(projectName)
    }

    override suspend fun updateTask(idTask: String, task: TaskDomain) {
        database.getTaskDao().updateTask(task.toEntity())
    }

    override suspend fun deleteTask(idTask: String){
        database.getTaskDao().deleteTask(idTask)
    }

    override suspend fun findLabelByName(labelName: String): String {
       return database.getLabelDao().getLabelIdByName(labelName)
    }


    //Labels
    override  fun getAllPersonalLabels(): Flow<List<LabelDomain>> {
       return  database.getLabelDao().getAllPersonalLabels().map{
           it.map{it.toDomain()}
       }

    }

    override suspend fun createPersonalLabel(label: LabelDomain) {
        database.getLabelDao().createPersonalLabel(label.toEntity())
    }

    override suspend fun getPersonalLabelById(idLabel: String): LabelDomain {
       return  database.getLabelDao().getPersonalLabelById(idLabel).toDomain()

    }

    override suspend fun updatePersonalLabelById(idLabel: String, label: LabelDomain) {
       database.getLabelDao().updatePersonalLabel(label.toEntity())
    }

    override suspend fun deleteLabelById(idLabel: String) {
        database.getLabelDao().deleteLabelById(idLabel)
    }

    override suspend fun clearAllProjectInDB() {
        database.getProjectDao().clearAllProjectInDB()
    }

    override suspend fun clearAllTaskInDB() {
        database.getTaskDao().clearAllTaskInDB()
    }

    override suspend fun clearAllLabelsInDB() {
        database.getLabelDao().clearAllLabels()
    }


}