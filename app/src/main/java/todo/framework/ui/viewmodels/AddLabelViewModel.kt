package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import todo.domain.usescases.CreatePersonalLabelUC
import todo.framework.Label
import javax.inject.Inject

@HiltViewModel
class AddLabelViewModel @Inject constructor( private val createLabel : CreatePersonalLabelUC): ViewModel() {

    fun createLabel(label :Label){
        viewModelScope.launch(Dispatchers.IO){
            createLabel.createPersonalLabel(label)
        }
    }

}