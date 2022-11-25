package todo.framework


import todo.domain.LabelDomain
import todo.domain.NetworkResources
import todo.domain.ProjectDomain
import todo.domain.ResponseState
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
    override suspend fun getAllProjects(): ResponseState<List<ProjectDomain>> {

        return try {
           val response= toDoApiServices.getAllProjects().map { it.toProjectDomain() }
            ResponseState.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseState.Error(e.toString(), emptyList())
        }
    }

    override suspend fun createProject(projectDto: ProjectDomain): ResponseState<ProjectDomain?> {
       return try {
           val response = toDoApiServices.createProject(projectDto.toProjectDto()).toProjectDomain()
           ResponseState.Success(response)

        } catch (e: Exception) {
            e.printStackTrace()
           ResponseState.Error(e.toString())
        }
    }

    override suspend fun getProjectById(idProject: String): ResponseState<ProjectDomain?> {
        return try {
            val response = toDoApiServices.getProjectById(idProject).toProjectDomain()
            ResponseState.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseState.Error(e.toString())
        }
    }

    override suspend fun updateProject(idProject: String, projectDto: ProjectDomain): ResponseState<ProjectDomain?> {
       return try {
           val response = toDoApiServices.updateProject(idProject, projectDto.toProjectDto()).toProjectDomain()
            ResponseState.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseState.Error(e.toString())
        }
    }

    override suspend fun deleteProject(idProject: String): ResponseState<Int> {
       return try {
            toDoApiServices.deleteProject(idProject)
            ResponseState.Success(1)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseState.Error(e.toString(),-1)
        }
    }


    override suspend fun getActiveTasks(): ResponseState<List<TaskDomain>> {
        return try {
            val response = toDoApiServices.getActiveTasks().map {
                it.toTaskDomain()
            }
            ResponseState.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseState.Error(e.toString(), emptyList())
        }
    }

    override suspend fun createNewTask(task: TaskDomain): ResponseState<TaskDomain?> {
       return try {
            val response = toDoApiServices.createNewTask(task.toTaskDto()).toTaskDomain()
            ResponseState.Success(response)
        }catch (e:Exception){
            e.printStackTrace()
           ResponseState.Error(e.toString())
        }
    }

    override suspend fun getAnActiveTaskById(idTask: String): ResponseState<TaskDomain?> {
        return try {
            val response= toDoApiServices.getAnActiveTaskById(idTask).toTaskDomain()
            ResponseState.Success(response)
        }catch (e:Exception){
            e.printStackTrace()
            ResponseState.Error(e.toString())
        }

    }

    override suspend fun updateTask(idTask: String, task: TaskDomain): ResponseState<TaskDomain?> {
       return try {
            val response = toDoApiServices.updateTask(idTask, task.toTaskDto()).toTaskDomain()
            ResponseState.Success(response)
        }catch (e:Exception){
            e.printStackTrace()
            ResponseState.Error(e.toString())
        }
    }


    override suspend fun deleteTask(idTask: String): ResponseState<Int> {
       return try {
           toDoApiServices.deleteTask(idTask)
           ResponseState.Success(1)
        }catch (e:Exception){
            e.printStackTrace()
           ResponseState.Error(e.toString(),-1)
        }
    }

    override suspend fun getAllPersonalLabels(): ResponseState<List<LabelDomain>> {
        return try {
            val response = toDoApiServices.getAllPersonalLabels().map {
                it.toLabelDomain()
            }
            ResponseState.Success(response)
        }catch (e:Exception){
            e.printStackTrace()
            ResponseState.Error(e.toString(), emptyList())
        }

    }

    override suspend fun createPersonalLabel(label: LabelDomain): ResponseState<LabelDomain?> {
       return try {
           val response =  toDoApiServices.createPersonalLabel(label.toLabelDto()).toLabelDomain()
            ResponseState.Success(response)
        }catch (e:Exception){
            e.printStackTrace()
           ResponseState.Error(e.toString())
        }

    }

    override suspend fun getPersonalLabelById(idLabel: String): ResponseState<LabelDomain?> {
        return try {
            val response = toDoApiServices.getPersonalLabelById(idLabel).toLabelDomain()
            ResponseState.Success(response)
        }catch (e:Exception){
            e.printStackTrace()
            ResponseState.Error(e.toString())
        }

    }

    override suspend fun updatePersonalLabelById(idLabel: String, label: LabelDomain): ResponseState<LabelDomain?> {
        return try {
            val response = toDoApiServices.updatePersonalLabelById(idLabel, label.toLabelDto()).toLabelDomain()
            ResponseState.Success(response)
        }catch (e:Exception){
            e.printStackTrace()
           ResponseState.Error(e.toString())
        }

    }

    override suspend fun deleteLabelById(idLabel: String): ResponseState<Int> {
       return try {
            toDoApiServices.deleteLabelById(idLabel)
           ResponseState.Success(1)
        }catch (e:Exception){
            e.printStackTrace()
           ResponseState.Error(e.toString(),-1)
        }

    }


}