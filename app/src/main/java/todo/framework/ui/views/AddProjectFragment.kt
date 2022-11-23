package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.AddProjectPageBinding
import dagger.hilt.android.AndroidEntryPoint
import todo.framework.Project
import todo.framework.ui.viewmodels.AddProjectViewModel

@AndroidEntryPoint
class AddProjectFragment: Fragment(R.layout.add_project_page) {
    private var _binding: AddProjectPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController:NavController
    private lateinit var colorCode:String
    private val viewModel : AddProjectViewModel by viewModels()
    private val colorPicker = ColorPickerBottomSheet{ colorCode ->
        this.colorCode = colorCode
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddProjectPageBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.toolbar.inflateMenu(R.menu.add_project_menu)

        binding.btnColor.setOnClickListener {
            showColorPicker()
        }
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.save -> {
                    viewModel.createProject(collectProjectData())
                    true
                }
                else -> false
            }
        }

    }
    private fun showColorPicker(){
        colorPicker.show(requireActivity().supportFragmentManager,"colorPicker")
    }
    private fun collectProjectData():Project{
        val projectName = binding.etProjectName.text.toString()
        val colorCode = this.colorCode
        val isFavorite = binding.switchFavorite.isChecked
        return Project(id ="", name = projectName, color = colorCode, isFavorite = isFavorite)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }



}