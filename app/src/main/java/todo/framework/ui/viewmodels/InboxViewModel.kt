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
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.CreateNewTaskUC
import todo.domain.usescases.DeleteTaskUC
import todo.domain.usescases.GetAllTaskUC
import todo.domain.usescases.SearchTaskUC
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class InboxViewModel
@Inject constructor(
    private val getAllTaskUC: GetAllTaskUC,
    private val searchTask: SearchTaskUC,
    private val deleteTaskUC: DeleteTaskUC,
    private val createNewTaskUC: CreateNewTaskUC
) : ViewModel() {

    private var _listOfInboxTask = MutableStateFlow<ResponseState<Flow<List<Task>>>>(ResponseState.Loading())
    val listOfInboxTask: StateFlow<ResponseState<Flow<List<Task>>>> get() = _listOfInboxTask

    private var _queryAswer = MutableStateFlow<List<Task>>(emptyList())
    val queryAswer: StateFlow<List<Task>> get() = _queryAswer

    private var _query = MutableStateFlow("")
    val query : StateFlow<String> get()= _query


    init {

        getAllTasks()

    }

    fun getAllTasks() = viewModelScope.launch {
        _listOfInboxTask.value = getAllTaskUC.getAllTasks()
    }

    fun searchTask(query: String) = viewModelScope.launch(Dispatchers.IO) {
        _query.value = query

        searchTask.searchTask(_query.value).data?.distinctUntilChanged()?.collect {
            _queryAswer.value = it
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