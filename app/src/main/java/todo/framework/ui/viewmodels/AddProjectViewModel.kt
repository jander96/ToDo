package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import todo.domain.usescases.CreateProjectUC
import todo.framework.Project
import javax.inject.Inject


@HiltViewModel
class AddProjectViewModel
@Inject constructor (private val createProject : CreateProjectUC): ViewModel() {

    fun createProject(project: Project){
        viewModelScope.launch(Dispatchers.IO) {
            createProject.createProject(project)
        }

    }
}