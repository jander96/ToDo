package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.CreateNewTaskUC
import todo.domain.usescases.DeleteProjectUC
import todo.domain.usescases.DeleteTaskUC
import todo.domain.usescases.GetAllProjectUC
import todo.framework.Project
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel
@Inject constructor(
    private val getAllProjectUC: GetAllProjectUC,
    private val deleteProjectUC: DeleteProjectUC,
    private val createNewTaskUC: CreateNewTaskUC
) : ViewModel() {

    private var _listOfAllProjects = MutableStateFlow<ResponseState<Flow<List<Project>>>>(ResponseState.Loading())
    val listOfAllProjects: StateFlow<ResponseState<Flow<List<Project>>>> get() = _listOfAllProjects


    init {

        getAllProjects()

    }

    fun getAllProjects() = viewModelScope.launch {
        _listOfAllProjects.value = getAllProjectUC.getAllProjects()
    }


    fun deleteProject(project :Project)= viewModelScope.launch(Dispatchers.IO){
       deleteProjectUC.deleteProject(project.id)

    }

    fun createTask(task:Task)= viewModelScope.launch(Dispatchers.IO){
        createNewTaskUC.creteNewTask(task)
    }


}