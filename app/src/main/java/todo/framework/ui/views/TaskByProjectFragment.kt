package todo.framework.ui.views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.TaskByProjectBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.framework.ui.adapters.TaskAdapter
import todo.framework.ui.viewmodels.TaskByProjectFragmentViewModel

@AndroidEntryPoint
class TaskByProjectFragment : Fragment(R.layout.task_by_project) {

    private var _binding: TaskByProjectBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val args: TaskByProjectFragmentArgs by navArgs()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private val viewModel: TaskByProjectFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = TaskByProjectBinding.bind(view)
        navController = findNavController()
        binding.toolbar.setupWithNavController(navController)

        setupRecycler()
        setupScreen()
        setupSwipeRefresh()

    }

    private fun setupRecycler() {
        recyclerView = binding.recyclerView


        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        adapter = TaskAdapter{task->
            val bundle = Bundle()
            bundle.putParcelable(AddTaskBottomSheet.TASK,task)
            navController.navigate(R.id.action_global_addTaskBottomSheet,bundle)
        }
        recyclerView.adapter = adapter

        recyclerView.layoutManager = linearLayoutManager

    }

    private fun filterListByProject() {
        Log.d("Filter","Se esta filtrando por Project")
        viewModel.filterTaskByProject(args.projectId!!)
    }
    private fun filterListByLabels(){
        Log.d("Filter","Se esta filtrando por label")
        viewModel.filterListByLLabels(args.label!!)
    }

    private fun setupScreen() {
        lifecycleScope.launch {

            viewModel.listaTask.collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        Log.d("Filter","Args projectId = ${args.projectId } Args labels = ${args.label}")
                        when{
                            args.projectId != null -> filterListByProject()
                            args.label != null -> filterListByLabels()
                        }

                        hideProgreesBar()
                        viewModel.listaTaskFiltred.collect{
                            adapter.submitList(it)
                            binding.swipe.isRefreshing = false
                        }
                    }

                    is ResponseState.Error -> {
                        hideProgreesBar()
                        binding.swipe.isRefreshing = false
                        binding.ivNetworkError.visibility = View.VISIBLE
                        binding.progress.visibility = View.INVISIBLE

                    }

                    is ResponseState.Loading -> {
                        showProgressBar()
                        binding.swipe.isRefreshing = true
                    }

                }
            }

        }

    }
    private fun swipeRecyclerViewToDelete(){
        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = adapter.currentList[position]
                viewModel.deleteTask(task)
                Snackbar.make(binding.root,"Successfully delete task", Snackbar.LENGTH_SHORT).apply{
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply{
            attachToRecyclerView(binding.recyclerView)
        }
    }


    private fun hideProgreesBar() {
        binding.progress.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun setupSwipeRefresh() {
        binding.swipe.setColorSchemeResources(
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green
        )
        binding.swipe.setOnRefreshListener {
            viewModel.getAllTaskList()
            setupScreen()

        }
    }

}