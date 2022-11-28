package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.CreateNewTaskUC
import todo.domain.usescases.DeleteTaskUC
import todo.domain.usescases.GetAllProjectUC
import todo.domain.usescases.GetAllTaskUC
import todo.domain.usescases.GetTaskByIdUC
import todo.domain.usescases.SearchTaskUC
import todo.framework.Project
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor (
    private val getActiveTaskUC: GetAllTaskUC,
    private val searchTask: SearchTaskUC,
    private val getAllProject: GetAllProjectUC,
    private val deleteTaskUC: DeleteTaskUC,
    private val createNewTaskUC: CreateNewTaskUC
): ViewModel() {


    private var _listaTask= MutableStateFlow<ResponseState<Flow<List<Task>>>>(ResponseState.Loading())
    val listaTask:StateFlow<ResponseState<Flow<List<Task>>>> get() = _listaTask

    private var _allProjectList = MutableStateFlow<ResponseState<Flow<List<Project>>>>(ResponseState.Loading())
    val allProjectList : StateFlow<ResponseState<Flow<List<Project>>>> get() = _allProjectList


    private var _queryAswer = MutableStateFlow<List<Task>>(emptyList())
    val queryAswer: StateFlow<List<Task>> get() = _queryAswer

    private var _query = MutableStateFlow("")
    val query : StateFlow<String> get()= _query



    init{
        getProjectList()
        getAllTaskList()
    }


    private fun getProjectList() =viewModelScope.launch(Dispatchers.IO) {
        _allProjectList.value = getAllProject.getAllProjects()
    }
     fun getAllTaskList()= viewModelScope.launch(Dispatchers.IO) {
       _listaTask.value = getActiveTaskUC.getAllTasks()
    }


    fun searchTask(query:String){
        _query.value = query

        viewModelScope.launch(Dispatchers.IO) {
            searchTask.searchTask(_query.value).data?.distinctUntilChanged()?.collect{
                _queryAswer.value = it
            }
        }
    }
    fun deleteTask(task:Task)= viewModelScope.launch(Dispatchers.IO){
        Log.d("Deleted","la task borrada tenia el id ${task.id}")
        deleteTaskUC.deleteTask(task.id)

    }

    fun createTask(task:Task)= viewModelScope.launch(Dispatchers.IO){
        createNewTaskUC.creteNewTask(task)
    }






}
