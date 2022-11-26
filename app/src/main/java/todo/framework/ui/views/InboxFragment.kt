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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.InboxPageBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.framework.room.TodoDataBase
import todo.framework.ui.adapters.TaskIboxAdapter
import todo.framework.ui.viewmodels.InboxViewModel
import javax.inject.Inject

@AndroidEntryPoint
class InboxFragment : Fragment(R.layout.inbox_page) {
    private var _binding: InboxPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController

    @Inject
    lateinit var database: TodoDataBase
    private val viewModel: InboxViewModel by viewModels()
    private lateinit var adapter: TaskIboxAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InboxPageBinding.bind(view)
        navController = findNavController()

        setupToolbar()
        setupRecyclerView()
        setupScreen()
        searchViewListener()
        swipeRecyclerViewToDelete()
        setupSwipeRefresh()

    }

    private fun searchTask(query: String?) {
        viewModel.searchTask("%$query%")
        updateListOfTask()
    }

    private fun updateListOfTask() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.queryAswer.collect {
                adapter.submitList(it)
            }
        }
    }

    private fun setupToolbar() {
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))

        binding.collapsingToolbar.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
        binding.toolbar.setupWithNavController(navController)



    }

    private fun setupScreen(){
        lifecycleScope.launch {

            viewModel.listOfInboxTask.collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        hideProgreesBar()
                        responseState.data.let { flow ->
                            flow?.collect {
                                adapter.submitList(it)
                                binding.swipe.isRefreshing = false

                            }
                        }


                    }

                    is ResponseState.Error -> {
                        hideProgreesBar()
                        binding.swipe.isRefreshing = false
                        binding.ivNetworkError.visibility = View.VISIBLE


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

    private fun setupRecyclerView() {
        recyclerView = binding.recyclerView
        adapter = TaskIboxAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

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
    fun swipeRecyclerViewToDelete(){
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
                Snackbar.make(binding.root,"Successfully delete task",Snackbar.LENGTH_SHORT).apply{
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerView)
        }

    }
    fun setupSwipeRefresh(){
        binding.swipe.setColorSchemeResources(R.color.red,R.color.orange,R.color.yellow,R.color.green)
        binding.swipe.setOnRefreshListener {
           viewModel.getAllTasks()
            setupScreen()

        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}