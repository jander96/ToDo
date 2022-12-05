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
import todo.domain.usescases.GetAllPersonalLabelsUC
import todo.domain.usescases.GetAllProjectUC
import todo.domain.usescases.GetAllTaskUC
import todo.framework.Project
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel
@Inject constructor(
    private val getAllProjectUC: GetAllProjectUC,
    private val getAllTaskUC: GetAllTaskUC,
    private val getAllPersonalLabelsUC: GetAllPersonalLabelsUC,
    private val deleteProjectUC: DeleteProjectUC
) : ViewModel() {

    private var _listOfAllProjects = MutableStateFlow<ResponseState<Flow<List<Project>>>>(ResponseState.Loading())
    val listOfAllProjects: StateFlow<ResponseState<Flow<List<Project>>>> get() = _listOfAllProjects


    init {

        getAllProjects()
        getAllTask()
        getAllLabels()

    }
    fun deleteProject(project :Project)= viewModelScope.launch(Dispatchers.IO){
        deleteProjectUC.deleteProject(project.id)
    }
    fun getAllProjects() = viewModelScope.launch(Dispatchers.IO) {
        _listOfAllProjects.value = ResponseState.Loading()
        _listOfAllProjects.value = getAllProjectUC.getAllProjects()
    }
    private fun getAllTask()= viewModelScope.launch(Dispatchers.IO) {
        getAllTaskUC.getAllTasks()
    }
    private fun getAllLabels() = viewModelScope.launch(Dispatchers.IO){
        getAllPersonalLabelsUC.getAllPersonalLabels()
    }



}