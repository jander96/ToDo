package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.AddProjectPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import todo.framework.Project
import todo.framework.ui.viewmodels.AddProjectViewModel

@AndroidEntryPoint
class AddProjectFragment : Fragment(R.layout.add_project_page) {
    private var _binding: AddProjectPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private var colorCode: String? = null
    private val viewModel: AddProjectViewModel by viewModels()
    private val colorPicker = ColorPickerBottomSheet { colorCode ->
        this.colorCode = colorCode
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddProjectPageBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.toolbar.inflateMenu(R.menu.add_project_menu)

        binding.btnColor.setOnClickListener {
            showColorPicker()
        }
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.save -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        withContext(Dispatchers.Main) {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.tvMessangeError.visibility = View.GONE
                            binding.animationConnectionError.visibility = View.GONE
                        }
                        val response = viewModel.createProject(collectProjectData())
                        withContext(Dispatchers.Main) {
                            binding.progressBar.visibility = View.GONE
                            if (response.data == null) {
                                binding.animationConnectionError.visibility = View.VISIBLE
                                binding.tvMessangeError.visibility = View.VISIBLE
                            } else navController.popBackStack()
                        }
                    }

                    true
                }

                else -> false
            }
        }

    }

    private fun showColorPicker() {
        colorPicker.show(requireActivity().supportFragmentManager, "colorPicker")
    }

    private fun collectProjectData(): Project {
        val projectName = binding.etProjectName.text.toString()
        val colorCode = this.colorCode
        val isFavorite = binding.switchFavorite.isChecked
        return Project(id = "", name = projectName, color = colorCode, isFavorite = isFavorite)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}