package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ProjectPickerPageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.framework.Label
import todo.framework.Project
import todo.framework.ui.adapters.LabelAdapter
import todo.framework.ui.adapters.ProjectAdapter
import todo.framework.ui.viewmodels.ProjectPickerViewModel

@AndroidEntryPoint
class ProjectPickerBottomSheet(private val projectPicked: (projectPicked: String) -> Unit) :
    BottomSheetDialogFragment(R.layout.project_picker_page) {
    private var _binding: ProjectPickerPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private val viewModel: ProjectPickerViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ProjectPickerPageBinding.bind(view)
        navController = findNavController()
        recyclerView = binding.recyclerView

        lifecycleScope.launch {
            viewModel.listOfProjects.collect { response ->
                when (response) {
                    is ResponseState.Error -> {

                    }

                    is ResponseState.Loading -> {

                    }

                    is ResponseState.Success -> {
                        response.data?.collect { listOfProjects ->
                            setupRecyclerView(listOfProjects)
                        }
                    }
                }
            }
        }

    }

    private fun setupRecyclerView(listProject: List<Project>) {
        val adapter = ProjectAdapter {
            projectPicked(it)
        }.apply {
            submitList(listProject)
        }

        recyclerView.adapter = adapter
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }
}