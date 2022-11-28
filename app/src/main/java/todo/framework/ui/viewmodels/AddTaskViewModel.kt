package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import todo.domain.DueDomain
import todo.domain.ResponseState
import todo.domain.usescases.CreateNewTaskUC
import todo.domain.usescases.GetProjectByIdUC
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel
@Inject constructor(
    private val createNewTaskUC: CreateNewTaskUC
) :
    ViewModel() {


    private var _responseState = MutableStateFlow<ResponseState<Task?>>(ResponseState.Loading())
    val responseState:StateFlow<ResponseState<Task?>> get()= _responseState

    fun onDateSelected(year: Int, month: Int, day: Int): String {
        return "$year-$month-$day"
    }

    fun onTimeSelected(hour: Int, minutes: Int): String {
        return "T$hour:$minutes:00"
    }

    fun onLabelSelected(labelName: String): String {
        return labelName
    }

    fun onProjectSelected(projectIdPicked: String): String {
        return projectIdPicked
    }





    suspend fun createTask(task: Task) {
        val response = createNewTaskUC.creteNewTask(task)
        _responseState.value= response

    }

}