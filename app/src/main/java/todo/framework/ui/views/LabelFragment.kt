package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
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
    private var childName: String? = null
    private  var groupPosition : Int? = null
    private val titleList = listOf("Projects", "Tags")
    private lateinit var listOfProjects: List<String>
    private lateinit var listOfLabels : List<String>

    private lateinit var map: HashMap<String,List<String>>

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
            view.findViewById<ConstraintLayout>(R.id.layout).background =
                AppCompatResources.getDrawable(requireContext(),R.color.blue)
            view.findViewById<ImageView>(R.id.iv_clear).visibility = View.VISIBLE
            if(groupPosition == 1)view.findViewById<ImageView>(R.id.iv_filter).visibility = View.VISIBLE

            view.findViewById<ImageView>(R.id.iv_clear).setOnClickListener {
                val childName = if (groupPosition == 0) {
                    listOfProjects[childPosition]
                } else {
                    listOfLabels[childPosition]
                }
                viewModel.deleteChild(groupPosition, childName)
            }

            view.findViewById<ImageView>(R.id.iv_filter).setOnClickListener {
                val label = viewModel.listOfAllLabels.value[childPosition].name
                if (groupPosition == 1) {
                    navController.navigate(
                        LabelFragmentDirections.actionLabelFragmentToTaskByProjectFragment(
                            label = label
                        )
                    )
                }
                if (groupPosition == 0) {

                }
            }




            false
        }




    }

    private fun setupToolbar() {
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }


    private suspend fun setupExpnableListView() {



        combine(
            viewModel.listOfAllProjects.map {it.map { it.name }},
            viewModel.listOfAllLabels.map { it.map{it.name}}

        ){ listOfProjects, listOfLabels ->
            this.listOfProjects = listOfProjects
            this.listOfLabels = listOfLabels
            hashMapOf(
                titleList[0] to listOfProjects,
                titleList[1] to listOfLabels
            )

        }.collect { map ->
            this.map = map
            val adapter = ProjectLabelsExpanableAdapter(requireContext(), titleList, map){ groupPosition,childName ->
                this.groupPosition = groupPosition
                this.childName = childName
            }
            expanableListView.setAdapter(adapter)

        }

    }


    private fun setupScreen(){
        lifecycleScope.launch(Dispatchers.Main) {

            viewModel.responseState.collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        setupExpnableListView()
                    }
                    is ResponseState.Error -> {
                        binding.ivNetworkError.visibility = View.VISIBLE
                    }
                    is ResponseState.Loading -> {
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