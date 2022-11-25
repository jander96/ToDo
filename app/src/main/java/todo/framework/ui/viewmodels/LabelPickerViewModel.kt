package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.GetAllPersonalLabelsUC
import todo.framework.Label
import javax.inject.Inject

@HiltViewModel
class LabelPickerViewModel
@Inject constructor (private val getAllPersonalLabelsUC: GetAllPersonalLabelsUC): ViewModel() {
    private  var _listOfLabels = MutableStateFlow<ResponseState<Flow<List<Label>>>>(ResponseState.Loading())
    val listOfLabels : StateFlow<ResponseState<Flow<List<Label>>>> get()= _listOfLabels

    init {
        getListOfLabels()
    }

    private fun getListOfLabels()= viewModelScope.launch {
        _listOfLabels.value = getAllPersonalLabelsUC.getAllPersonalLabels()
    }

}