package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.domain.usescases.CreateProjectUC
import todo.framework.Project
import javax.inject.Inject


@HiltViewModel
class AddProjectViewModel
@Inject constructor (private val createProject : CreateProjectUC): ViewModel() {

   suspend fun createProject(project: Project): ResponseState<Project?>{
           return createProject.createProject(project)
    }
}