package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.GetAllTaskUC
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class TaskByProjectFragmentViewModel @Inject constructor(
    private val getActiveTaskUC: GetAllTaskUC
):ViewModel() {

    private var _listaTask= MutableStateFlow<ResponseState<Flow<List<Task>>>>(ResponseState.Loading())
    val listaTask : StateFlow<ResponseState<Flow<List<Task>>>> get()= _listaTask

    private var _listaTaskFiltred= MutableStateFlow<List<Task>>(emptyList())
    val listaTaskFiltred: StateFlow<List<Task>> get() = _listaTaskFiltred


    init{
        getAllTaskList()
    }

    fun getAllTaskList()= viewModelScope.launch(Dispatchers.IO) {
        _listaTask.value = getActiveTaskUC.getAllTasks()
    }

    fun filterTaskByProject(projectId:String)= viewModelScope.launch{
        _listaTask.value.data?.map{listTask->
            listTask.filter {
                it.projectId == projectId
            }
        }?.collect{
            _listaTaskFiltred.value = it
        }
    }

    fun filterListByLLabels(label: String) {

        _listaTask.value.data?.map {listTask ->
            listTask.filter { task->
                isLabelInArray(label ,task.labels)
            }
        }
    }


    fun isLabelInArray(label:String,labels : Array<String>?): Boolean{
        return if(labels != null ) label in labels
        else false
    }







}