package todo.framework.ui.viewmodels

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
import todo.domain.usescases.FindProjectByNameUC
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel
@Inject constructor(
    private val findProjectByNameUC: FindProjectByNameUC,
    private val createNewTaskUC: CreateNewTaskUC
) :
    ViewModel() {
    private var _taskBuilded = MutableStateFlow<Task?>(null)
    val taskBuilded: StateFlow<Task?> get() = _taskBuilded

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

    fun onProjectSelected(projectName: String): String {
        return projectName
    }

    private suspend fun findProjectIdByName(projectName: String): String {
        return findProjectByNameUC.findProjectIdByName(projectName)
    }

    fun buildTask(
        content: String,
        description: String? =null,
        due: DueDomain?= null,
        labelName: String?=null,
        projectName: String?= null
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            var projectId:String? = null
            if(projectName!=null) projectId =findProjectIdByName(projectName)
            _taskBuilded.value = Task(
                id = "",
                content = content,
                projectId = projectId,
                description = description,
                due = due,
                labels = arrayOf(labelName)
            )


        }

    }

    fun createTask(task: Task)= viewModelScope.launch(Dispatchers.IO){
        val response = createNewTaskUC.creteNewTask(task)
        _responseState.value= response

    }

}