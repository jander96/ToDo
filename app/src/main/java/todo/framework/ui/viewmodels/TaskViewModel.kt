package todo.framework.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import todo.domain.Repo
import todo.domain.usescases.GetActiveTaskUC
import todo.framework.Task
import javax.inject.Inject

@HiltViewModel
class TaskViewModel
@Inject
constructor(val repo: Repo): ViewModel() {
    private val defaultTask = Task("",null,null,"",null,null,
    null,null,null,null,null,null,null,null,null,
    null,null)

    private var _allTaskInProjects = GetActiveTaskUC(repo).getActiveTask()
    val allTaskInProjects get()=_allTaskInProjects
}