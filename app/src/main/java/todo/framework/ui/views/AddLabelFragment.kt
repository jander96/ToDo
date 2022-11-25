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
import com.example.todo.databinding.AddLabelPageBinding
import com.example.todo.databinding.AddProjectPageBinding
import dagger.hilt.android.AndroidEntryPoint
import todo.framework.Label
import todo.framework.Project
import todo.framework.ui.viewmodels.AddLabelViewModel
@AndroidEntryPoint
class AddLabelFragment:Fragment(R.layout.add_label_page) {

    private var _binding: AddLabelPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel : AddLabelViewModel by viewModels()
    private var colorCode:String? = null
    private val colorPicker = ColorPickerBottomSheet{ colorCode ->
        this.colorCode = colorCode
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddLabelPageBinding.bind(view)
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
                    viewModel.createLabel(collectLabelData())
                    true
                }
                else -> false
            }
        }

    }
    private fun showColorPicker(){
        colorPicker.show(requireActivity().supportFragmentManager,"colorPicker")
    }

    private fun collectLabelData(): Label {
        val labelName = binding.etProjectName.text.toString()
        val colorCode = this.colorCode
        val isFavorite = binding.switchFavorite.isChecked
        return Label(id = "",name = labelName, color = colorCode, isFavorite = isFavorite)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }




}