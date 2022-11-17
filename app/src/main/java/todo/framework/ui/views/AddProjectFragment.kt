package todo.framework.ui.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.AddLabelPageBinding
import com.example.todo.databinding.AddProjectPageBinding

class AddProjectFragment: Fragment(R.layout.add_project_page) {
    private var _binding: AddProjectPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController:NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddProjectPageBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.toolbar.inflateMenu(R.menu.add_project_menu)

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }



}