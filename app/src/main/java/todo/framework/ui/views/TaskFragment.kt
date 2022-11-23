package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.TaskPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import todo.framework.ui.ScreenState
import todo.framework.ui.adapters.TaskAdapter
import todo.framework.ui.viewmodels.TaskViewModel

@AndroidEntryPoint
class TaskFragment: Fragment(R.layout.task_page) {
    private var _binding: TaskPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner : Spinner

    private lateinit var adapter :TaskAdapter
    

    private val viewModel: TaskViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= TaskPageBinding.bind(view)
        navController = findNavController()

        setupToolbar()
        setupProgress()
        setupSpinner()
        setupRecycler()
        searchViewListener()

    }

    private fun setupProgress() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.screenState.collect { screenState ->

                when (screenState) {
                    ScreenState.LOADING -> binding.progress.visibility = View.VISIBLE
                    ScreenState.SUSCCES -> binding.progress.visibility = View.INVISIBLE
                    else -> {
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.ivNetworkError.visibility = View.VISIBLE
                        binding.progress.visibility = View.INVISIBLE
                    }
                }

            }
        }
    }

    private fun setupRecycler(){
        recyclerView = binding.recyclerView
        spinner = binding.spinner

        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
         adapter = TaskAdapter()
        recyclerView.adapter = adapter

        recyclerView.layoutManager = linearLayoutManager

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.listaTask.collect {
                adapter.submitList(it)
            }
        }
    }
    private fun setupSpinner() = runBlocking {
        spinner = binding.spinner
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.allProjectList.collect {


            }
        }


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
        //binding.toolbar.inflateMenu(R.menu.main_menu)
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}