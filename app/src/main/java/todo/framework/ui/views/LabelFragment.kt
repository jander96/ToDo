package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.LabelPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.framework.ui.adapters.ProjectLabelsExpanableAdapter
import todo.framework.ui.viewmodels.LabelViewModel

@AndroidEntryPoint
class LabelFragment : Fragment(R.layout.label_page) {
    private var _binding: LabelPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var expanableListView: ExpandableListView
    private val viewModel: LabelViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LabelPageBinding.bind(view)
        navController = findNavController()
        expanableListView = binding.expandableListView

        setupToolbar()
        setupScreen()


        expanableListView.setOnGroupClickListener { expandableListView, view, i, l ->
            view.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
                if (i == 0) {
                    navController.navigate(R.id.action_label_to_addProject)
                } else navController.navigate(R.id.action_label_to_addLabel)
            }
            false
        }


        expanableListView.setOnChildClickListener { expandableListView, view, groupPosition, childPosition, l ->
            true
        }




    }

    private fun setupToolbar() {
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }




    private suspend fun setupExpnableListView() {

        val titleList = listOf("Projects", "Tags")

        combine(
            viewModel.listOfAllProjects.map {it.map { it.name }},
            viewModel.listOfAllLabels.map { it.map{it.name}}

        ){ listOfProjects, listOfLabels ->


            hashMapOf(
                titleList[0] to listOfProjects,
                titleList[1] to listOfLabels
            )

        }.collect { map ->

            val adapter = ProjectLabelsExpanableAdapter(requireContext(), titleList, map)
            expanableListView.setAdapter(adapter)


        }


    }

    private fun hideProgreesBar() {
        binding.progress.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
    }
    private fun setupScreen(){
        lifecycleScope.launch(Dispatchers.Main) {

            viewModel.responseState.collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        hideProgreesBar()
                        setupExpnableListView()
                    }

                    is ResponseState.Error -> {
                        hideProgreesBar()
                        binding.ivNetworkError.visibility = View.VISIBLE
                        binding.progress.visibility = View.INVISIBLE

                    }

                    is ResponseState.Loading -> {
                        showProgressBar()
                    }

                }
            }

        }

    }



    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}