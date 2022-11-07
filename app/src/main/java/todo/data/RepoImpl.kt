package todo.data

import todo.domain.CollaboratorsDomain
import todo.domain.LabelDomain
import todo.domain.NetworkResources
import todo.domain.ProjectDomain
import todo.domain.RenamedLabelDomain
import todo.domain.TaskDomain

class RepoImpl(private val networkResources: NetworkResources) : todo.domain.Repo {

    override suspend fun getAllProjects(): List<ProjectDomain> {
        return networkResources.getAllProjects()
    }

    override suspend fun createProject(projectDomain: ProjectDomain) {
        networkResources.createProject(projectDomain)
    }

    override suspend fun getProjectById(idProject: String): ProjectDomain {
        return networkResources.getProjectById(idProject)
    }

    override suspend fun updateProject(idProject: String, projectDomain: ProjectDomain) {
        networkResources.updateProject(idProject, projectDomain)
    }

    override suspend fun getAllProjectCollaborators(idProject: String): List<CollaboratorsDomain> {
        return networkResources.getAllProjectCollaborators(idProject)
    }

    override suspend fun getActiveTasks(): List<TaskDomain> {
        return networkResources.getActiveTasks()
    }

    override suspend fun createNewTask(task: TaskDomain) {
        networkResources.createNewTask(task)
    }

    override suspend fun getAnActiveTaskById(idTask: String) {
        networkResources.getAnActiveTaskById(idTask)
    }

    override suspend fun updateTask(idTask: String, task: TaskDomain) {
        networkResources.updateTask(idTask, task)
    }

    override suspend fun closeTask(idTask: String) {
        networkResources.closeTask(idTask)
    }

    override suspend fun reopenTask(idTask: String) {
        networkResources.reopenTask(idTask)
    }

    override suspend fun deleteTask(idTask: String) {
        networkResources.deleteTask(idTask)
    }

    override suspend fun getAllPersonalLabels(): List<LabelDomain> {
        return networkResources.getAllPersonalLabels()
    }

    override suspend fun createPersonalLabel(label: LabelDomain) {
        networkResources.createPersonalLabel(label)
    }

    override suspend fun getPersonalLabelById(idLabel: String) {
        networkResources.getPersonalLabelById(idLabel)
    }

    override suspend fun updatePersonalLabelById(idLabel: String, label: LabelDomain) {
        networkResources.updatePersonalLabelById(idLabel, label)
    }

    override suspend fun deleteLabelById(idLabel: String) {
        networkResources.deleteLabelById(idLabel)
    }

    override suspend fun getAllSharedLabels(): List<String> {
        return networkResources.getAllSharedLabels()
    }

    override suspend fun renameSharedLabels(renamedLabelDomain: RenamedLabelDomain) {
        networkResources.renameSharedLabels(renamedLabelDomain)
    }

    override suspend fun removeSharedLabels(labelName: String) {
        networkResources.removeSharedLabels(labelName)
    }
}