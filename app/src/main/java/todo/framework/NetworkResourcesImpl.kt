package todo.framework



import todo.domain.CollaboratorsDomain
import todo.domain.LabelDomain
import todo.domain.ProjectDomain
import todo.domain.RenamedLabelDomain
import todo.domain.TaskDomain
import todo.toProjectDomain
import todo.framework.network.ToDoApiServices
import todo.toCollaboratorsDomain
import todo.toLabelDomain
import todo.toLabelDto
import todo.toProjectDto
import todo.toRenamedLabelDto
import todo.toTaskDomain
import todo.toTaskDto

class NetworkResourcesImpl(private val toDoApiServices: ToDoApiServices):
    todo.domain.NetworkResources {
    override suspend fun getAllProjects(): List<ProjectDomain> {
       return toDoApiServices.getAllProjects().map {
           it.toProjectDomain()
       }
    }

    override suspend fun createProject(projectDto: ProjectDomain) {
        toDoApiServices.createProject(projectDto.toProjectDto())
    }

    override suspend fun getProjectById(idProject: String): ProjectDomain {
       return toDoApiServices.getProjectById(idProject).toProjectDomain()
    }

    override suspend fun updateProject(idProject: String, projectDto: ProjectDomain) {
        toDoApiServices.updateProject(idProject,projectDto.toProjectDto())
    }

    override suspend fun getAllProjectCollaborators(idProject: String): List<CollaboratorsDomain> {
      return  toDoApiServices.getAllProjectCollaborators(idProject).map {
          it.toCollaboratorsDomain()
      }
    }

    override suspend fun getActiveTasks(): List<TaskDomain> {
        return toDoApiServices.getActiveTasks().map {
            it.toTaskDomain()
        }
    }

    override suspend fun createNewTask(task: TaskDomain) {
        toDoApiServices.createNewTask(task.toTaskDto())
    }

    override suspend fun getAnActiveTaskById(idTask:String) {
        toDoApiServices.getAnActiveTaskById(idTask)
    }

    override suspend fun updateTask(idTask: String, task: TaskDomain) {
        toDoApiServices.updateTask(idTask,task.toTaskDto())
    }

    override suspend fun closeTask(idTask: String) {
        toDoApiServices.closeTask(idTask)
    }

    override suspend fun reopenTask(idTask: String) {
        toDoApiServices.reopenTask(idTask)
    }

    override suspend fun deleteTask(idTask: String) {
        toDoApiServices.deleteTask(idTask)
    }

    override suspend fun getAllPersonalLabels(): List<LabelDomain> {
       return toDoApiServices.getAllPersonalLabels().map {
           it.toLabelDomain()
       }
    }

    override suspend fun createPersonalLabel(label: LabelDomain) {
        toDoApiServices.createPersonalLabel(label.toLabelDto())
    }

    override suspend fun getPersonalLabelById(idLabel: String) {
        toDoApiServices.getPersonalLabelById(idLabel)
    }

    override suspend fun updatePersonalLabelById(idLabel: String, label: LabelDomain) {
        return toDoApiServices.updatePersonalLabelById(idLabel,label.toLabelDto())
    }

    override suspend fun deleteLabelById(idLabel: String) {
        toDoApiServices.deleteLabelById(idLabel)
    }

    override suspend fun getAllSharedLabels(): List<String> {
       return toDoApiServices.getAllSharedLabels()
    }

    override suspend fun renameSharedLabels(renamedLabel: RenamedLabelDomain) {
        toDoApiServices.renameSharedLabels(renamedLabel.toRenamedLabelDto())
    }

    override suspend fun removeSharedLabels(labelName: String) {
        toDoApiServices.removeSharedLabels(labelName)
    }
}