package todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.todo.databinding.LayoutTesterBinding
import kotlinx.coroutines.launch
import todo.data.RepoImpl
import todo.domain.usescases.CreateProjectUC
import todo.domain.usescases.GetAllProjectUC
import todo.framework.NetworkResourcesImpl
import todo.framework.Project
import todo.framework.network.TodoApiByRetrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: LayoutTesterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutTesterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repo =  RepoImpl(NetworkResourcesImpl(TodoApiByRetrofit.retrofitServices))
        val project = Project(
            "220325183",
            "Projecto de Josias",
            "charcoal",
            "220325187",
            1,
            10,
            isShared = false,
            isFavorite = false,
            isInboxProject = false,
            isTeamInbox = false,
            viewStyle = "list",
            url = "https://todoist.com/showProject?id=2203306141"
        )
        binding.crearProject.setOnClickListener {
            lifecycleScope.launch {
                CreateProjectUC(
                   repo
                ).createProject(project)
            }
        }
        binding.getAllProject.setOnClickListener {
            lifecycleScope.launch {
                GetAllProjectUC(repo).getAllProjects()
            }
        }

    }
}