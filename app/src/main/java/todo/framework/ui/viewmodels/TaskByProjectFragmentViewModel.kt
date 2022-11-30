package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.GettAllTaskFromDBUC
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class TaskByProjectFragmentViewModel @Inject constructor(
    private val gettAllTaskDb : GettAllTaskFromDBUC
):ViewModel() {

    private var _listaTask= MutableStateFlow<ResponseState<Flow<List<Task>>>>(ResponseState.Loading())
    val listaTask : StateFlow<ResponseState<Flow<List<Task>>>> get()= _listaTask

    private var _listaTaskFiltred= MutableStateFlow<List<Task>>(emptyList())
    val listaTaskFiltred: StateFlow<List<Task>> get() = _listaTaskFiltred


    init{
        getAllTaskList()
    }

    fun getAllTaskList()= viewModelScope.launch(Dispatchers.IO) {
        _listaTask.value = gettAllTaskDb.getAllLabelsDb()
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

    fun filterListByLLabels(label: String)=viewModelScope.launch {
        _listaTask.value.data?.map {listTask ->
            listTask.filter { task->
                task.labels?.contains(label) ?: false
            }
        }?.collect{
            _listaTaskFiltred.value = it
        }
    }








}