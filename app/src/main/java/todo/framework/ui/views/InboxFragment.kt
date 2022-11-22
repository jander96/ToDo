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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.InboxPageBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import todo.framework.room.TodoDataBase
import todo.framework.ui.ScreenState.*
import todo.framework.ui.adapters.TaskIboxAdapter
import todo.framework.ui.viewmodels.InboxViewModel
import javax.inject.Inject
@AndroidEntryPoint
class InboxFragment: Fragment(R.layout.inbox_page) {
    private  var _binding:InboxPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    @Inject
    lateinit var database : TodoDataBase
    private val viewModel : InboxViewModel by viewModels()

    private lateinit var adapter : TaskIboxAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InboxPageBinding.bind(view)
        navController = findNavController()

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))

        binding.collapsingToolbar.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
        binding.toolbar.setupWithNavController(navController)
        //binding.toolbar.inflateMenu(R.menu.main_menu)
        setupProgress()


        binding.search.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchTask(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                searchTask(query)
                return false
            }

        })


        recyclerView = binding.recyclerView
         adapter = TaskIboxAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.listOfInboxTask.collect {
                adapter.submitList(it)
            }
        }

    }

    private fun searchTask(query:String?) {
        viewModel.searchTask("%$query%")
        updateListOfTask()

    }

    private fun updateListOfTask() {
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.queryAswer.collect{
                adapter.submitList(it)
            }
        }
    }
    private fun setupProgress() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.screenState.collect { screenState ->

                when (screenState) {
                    LOADING -> binding.progress.visibility = View.VISIBLE
                    SUSCCES -> binding.progress.visibility = View.INVISIBLE
                    else -> {
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.ivNetworkError.visibility = View.VISIBLE
                        binding.progress.visibility = View.INVISIBLE
                    }
                }

            }
        }
    }


    override fun onDestroy() {
        _binding= null
        super.onDestroy()
    }
}