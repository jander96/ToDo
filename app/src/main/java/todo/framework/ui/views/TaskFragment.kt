package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.TaskPageBinding

class TaskFragment: Fragment(R.layout.task_page) {
    private var _binding: TaskPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= TaskPageBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.collapsingToolbar.setupWithNavController(binding.toolbar,navController,appBarConfiguration)
        binding.toolbar.inflateMenu(R.menu.main_menu)
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}