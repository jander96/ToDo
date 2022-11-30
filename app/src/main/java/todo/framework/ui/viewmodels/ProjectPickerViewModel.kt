package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.GetAllProjectUC
import todo.domain.usescases.GetAllProjectsFromDBUC
import todo.framework.Project
import javax.inject.Inject

@HiltViewModel
class ProjectPickerViewModel
@Inject constructor ( private val getAllProjectUC: GetAllProjectsFromDBUC): ViewModel() {
    private  var _listOfProjects = MutableStateFlow<ResponseState<Flow<List<Project>>>>(ResponseState.Loading())
    val listOfProjects : StateFlow<ResponseState<Flow<List<Project>>>> get()= _listOfProjects

    init {
        getAllProjects()
    }

    fun getAllProjects()= viewModelScope.launch {
        _listOfProjects.value = getAllProjectUC.getAllProjectsFromDb()
    }

}