package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import todo.domain.usescases.GetAllPersonalLabelsUC
import todo.domain.usescases.GetAllProjectUC
import todo.framework.Label
import todo.framework.Project
import todo.framework.ui.ScreenState
import javax.inject.Inject

@HiltViewModel
class LabelViewModel @Inject constructor(
    private val getAllProjectUC: GetAllProjectUC,
    private val getAllLabelsUc: GetAllPersonalLabelsUC
) : ViewModel() {
    private var _listOfAllProjects = MutableStateFlow<List<Project>>(emptyList())
    val listOfAllProjects: StateFlow<List<Project>> get()= _listOfAllProjects

    private var _listOfAllLabels = MutableStateFlow<List<Label>>(emptyList())
    val listOfAllLabels : StateFlow<List<Label>> get()= _listOfAllLabels

    private var _screenState = MutableStateFlow(ScreenState.LOADING)
    val screenState: StateFlow<ScreenState> get()= _screenState

    init{
        viewModelScope.launch(Dispatchers.IO) {
            getAllLabelsUc.getAllPersonalLabels().collect{
                _listOfAllLabels.value = it
                setScreenState()
            }
        }

        viewModelScope.launch {
            getAllProjectUC.getAllProjects().collect{
                _listOfAllProjects.value = it
            }
        }
    }


    private fun setScreenState(){
        Log.d("ScreenState","Se esta seteando el estado de la pantalla")
        _screenState.value = ScreenState.LOADING
        if(_listOfAllLabels.value.isEmpty()){
            _screenState.value= ScreenState.FAIL
        }else{
            _screenState.value = ScreenState.SUSCCES
        }
    }
}