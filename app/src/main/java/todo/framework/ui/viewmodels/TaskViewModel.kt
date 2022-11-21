package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import todo.domain.usescases.GetAllTaskUC
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor (private val getActiveTaskUC: GetAllTaskUC): ViewModel() {


    private var _listaTask= MutableStateFlow<List<Task>>(emptyList())
    val listaTask:StateFlow<List<Task>> get() = _listaTask
    init{
        Log.d("viewModel","View Model Bien Ijectado")
        viewModelScope.launch(Dispatchers.Main) {
            getAllTaskInProject().collect{
                _listaTask.value= it
            }
        }

    }




    private suspend fun getAllTaskInProject(): Flow<List<Task>> {
       return  getActiveTaskUC.getAllTasks()
    }
}
