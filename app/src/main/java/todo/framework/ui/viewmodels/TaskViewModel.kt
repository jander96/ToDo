package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import todo.domain.Repo
import todo.domain.usescases.GetActiveTaskUC
import todo.framework.Task
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class TaskViewModel @Inject constructor (private val repo: Repo): ViewModel() {
    init{
        Log.d("viewModel","View Model Bien Ijectado")
        viewModelScope.launch(Dispatchers.Main) {
            _listaTask.value= getAllTaskInProject()
        }

    }


     private var _listaTask=MutableLiveData<List<Task>>()
    val listaTask:LiveData<List<Task>> get() = _listaTask


    private suspend fun getAllTaskInProject(): List<Task> {
       return  GetActiveTaskUC(repo).getActiveTask()
    }
}
