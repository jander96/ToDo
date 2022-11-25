package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.TaskPageBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import todo.domain.ResponseState
import todo.framework.ui.adapters.TaskAdapter
import todo.framework.ui.viewmodels.TaskViewModel

@AndroidEntryPoint
class TaskFragment: Fragment(R.layout.task_page) {
    private var _binding: TaskPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView


    private lateinit var adapter :TaskAdapter
    

    private val viewModel: TaskViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= TaskPageBinding.bind(view)
        navController = findNavController()

        setupToolbar()
        setupRecycler()
        setupScreen()
        swipeRecyclerViewToDelete()
        searchViewListener()
        setupSwipeRefresh()

    }


    private fun setupRecycler(){
        recyclerView = binding.recyclerView


        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
         adapter = TaskAdapter()
        recyclerView.adapter = adapter

        recyclerView.layoutManager = linearLayoutManager

    }

    private fun searchTask(query: String?) {
        viewModel.searchTask("%$query%")
        updateTaskQuery()
    }

    private fun searchViewListener() {
        binding.search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchTask(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                searchTask(query)
                return false
            }

        })
    }

    private fun updateTaskQuery() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.queryAswer.collect {
                adapter.submitList(it)
            }
        }
    }
    private fun setupToolbar(){
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.collapsingToolbar.setupWithNavController(binding.toolbar,navController,appBarConfiguration)

    }
    private fun setupScreen(){
        lifecycleScope.launch {

            viewModel.listaTask.collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        hideProgreesBar()
                        responseState.data.let { flow ->
                            flow?.collect {
                                adapter.submitList(it)
                                delay(5000)
                                binding.swipe.isRefreshing = false
                            }
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
    private fun hideProgreesBar() {
        binding.progress.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
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
    }
    fun setupSwipeRefresh(){
        binding.swipe.setColorSchemeResources(R.color.red,R.color.orange,R.color.yellow,R.color.green)
        binding.swipe.setOnRefreshListener {
            viewModel.getAllTaskList()
            setupScreen()

        }
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}