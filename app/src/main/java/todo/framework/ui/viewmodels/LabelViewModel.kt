package todo.framework.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.GetAllPersonalLabelsUC
import todo.domain.usescases.GetAllProjectUC
import todo.framework.Label
import todo.framework.Project
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

    private var _responseState  = MutableStateFlow<ResponseState<Flow<List<Label>>>>(ResponseState.Loading())
    val responseState: StateFlow<ResponseState<Flow<List<Label>>>> get()= _responseState


    init{
       getListOfAlllabels()
        getListOfAllProjects()
        updateResponse()
    }

    private fun getListOfAlllabels() = viewModelScope.launch(Dispatchers.IO) {

        getAllLabelsUc.getAllPersonalLabels().data?.collect {
            _listOfAllLabels.value = it

        }

    }
    private fun getListOfAllProjects() = viewModelScope.launch(Dispatchers.IO) {
        getAllProjectUC.getAllProjects().data?.collect{
            _listOfAllProjects.value = it

        }
    }

    private fun updateResponse()= viewModelScope.launch{
        val response = getAllLabelsUc.getAllPersonalLabels()
        _responseState.value = response
    }


}