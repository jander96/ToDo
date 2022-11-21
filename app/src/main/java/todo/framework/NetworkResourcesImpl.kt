package todo.framework


import todo.domain.LabelDomain
import todo.domain.NetworkResources
import todo.domain.ProjectDomain
import todo.domain.TaskDomain
import todo.toProjectDomain
import todo.framework.network.ToDoApiServices
import todo.toLabelDomain
import todo.toLabelDto
import todo.toProjectDto
import todo.toTaskDomain
import todo.toTaskDto
import javax.inject.Inject


class NetworkResourcesImpl @Inject constructor(private val toDoApiServices: ToDoApiServices) :
    NetworkResources {
    override suspend fun getAllProjects(): List<ProjectDomain> {
        return try {
            toDoApiServices.getAllProjects().map {
                it.toProjectDomain()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun createProject(projectDto: ProjectDomain): ProjectDomain? {
       return try {
            toDoApiServices.createProject(projectDto.toProjectDto()).toProjectDomain()

        } catch (e: Exception) {
            e.printStackTrace()
           null
        }
    }

    override suspend fun getProjectById(idProject: String): ProjectDomain? {
        return try {
            toDoApiServices.getProjectById(idProject).toProjectDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun updateProject(idProject: String, projectDto: ProjectDomain): ProjectDomain? {
       return try {
            toDoApiServices.updateProject(idProject, projectDto.toProjectDto()).toProjectDomain()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deleteProject(idProject: String): Int {
       return try {
            toDoApiServices.deleteProject(idProject)
            1
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }


    override suspend fun getActiveTasks(): List<TaskDomain> {
        return try {
            toDoApiServices.getActiveTasks().map {
                it.toTaskDomain()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun createNewTask(task: TaskDomain): TaskDomain?{
       return try {
            toDoApiServices.createNewTask(task.toTaskDto()).toTaskDomain()

        }catch (e:Exception){
            e.printStackTrace()
           null
        }
    }

    override suspend fun getAnActiveTaskById(idTask: String): TaskDomain? {
        return try {
             toDoApiServices.getAnActiveTaskById(idTask).toTaskDomain()
        }catch (e:Exception){
            e.printStackTrace()
            null
        }

    }

    override suspend fun updateTask(idTask: String, task: TaskDomain): TaskDomain? {
       return try {
            toDoApiServices.updateTask(idTask, task.toTaskDto()).toTaskDomain()

        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }


    override suspend fun deleteTask(idTask: String):Int {
       return try {
            toDoApiServices.deleteTask(idTask)
           1
        }catch (e:Exception){
            e.printStackTrace()
           -1
        }
    }

    override suspend fun getAllPersonalLabels(): List<LabelDomain> {
        return try {
             toDoApiServices.getAllPersonalLabels().map {
                it.toLabelDomain()
            }
        }catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }

    }

    override suspend fun createPersonalLabel(label: LabelDomain): LabelDomain? {
       return try {
            toDoApiServices.createPersonalLabel(label.toLabelDto()).toLabelDomain()

        }catch (e:Exception){
            e.printStackTrace()
           null
        }

    }

    override suspend fun getPersonalLabelById(idLabel: String): LabelDomain? {
        return try {
             toDoApiServices.getPersonalLabelById(idLabel).toLabelDomain()
        }catch (e:Exception){
            e.printStackTrace()
            null
        }

    }

    override suspend fun updatePersonalLabelById(idLabel: String, label: LabelDomain): LabelDomain? {
        return try {
             toDoApiServices.updatePersonalLabelById(idLabel, label.toLabelDto()).toLabelDomain()

        }catch (e:Exception){
            e.printStackTrace()
            null
        }

    }

    override suspend fun deleteLabelById(idLabel: String):Int {
       return try {
            toDoApiServices.deleteLabelById(idLabel)
           1
        }catch (e:Exception){
            e.printStackTrace()
           -1
        }

    }


}