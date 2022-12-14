package todo.framework.ui.views


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.InboxPageBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import todo.domain.ResponseState
import todo.framework.room.TodoDataBase
import todo.framework.ui.adapters.ProjectHomeAdapter
import todo.framework.ui.viewmodels.ProjectViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ProjectFragment : Fragment(R.layout.inbox_page) {
    private var _binding: InboxPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    @Inject
    lateinit var database: TodoDataBase
    private val viewModel: ProjectViewModel by viewModels()
    private lateinit var adapter: ProjectHomeAdapter
    companion object{
        const val PREFNAME = "user_data_pref"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = arguments?.getString(LoginFragment.USERNAME)
        val pref = requireContext().getSharedPreferences(PREFNAME,Context.MODE_PRIVATE).edit()
        pref.putString("user",username)
        pref.apply()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InboxPageBinding.bind(view)
        navController = findNavController()
        setupToolbar()
        setupRecyclerView()
        setupScreen()
        swipeRecyclerViewToDelete()
        setupSwipeRefresh()
    }
    private fun setupToolbar() {

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.loginFragment,R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))

        binding.collapsingToolbar.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
        binding.toolbar.setupWithNavController(navController)

    }
    private fun setupScreen() {
        lifecycleScope.launchWhenResumed {

            viewModel.listOfAllProjects.collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        responseState.data.let { flow ->
                            flow?.collect {
                                adapter.submitList(it)
                                withContext(Dispatchers.Main) {
                                    binding.swipe.isRefreshing = false
                                    binding.animationNoInternet.visibility = View.GONE
                                }
                            }
                        }
                    }
                    is ResponseState.Error -> {
                        withContext(Dispatchers.Main) {
                            binding.swipe.isRefreshing = false
                            binding.animationNoInternet.visibility = View.VISIBLE
                            binding.animationNoInternet.playAnimation()
                        }
                    }
                    is ResponseState.Loading -> {
                        withContext(Dispatchers.Main) {
                            binding.swipe.isRefreshing = true
                            binding.animationNoInternet.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
    private fun setupRecyclerView() {
        recyclerView = binding.recyclerView
        adapter = ProjectHomeAdapter() {
            filterListByProject(it)
        }
        val layoutManager =
            GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }
    private fun filterListByProject(projectId: String) {
        navController.navigate(ProjectFragmentDirections.actionProjectToTaskByProject(projectId))
    }
    fun swipeRecyclerViewToDelete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
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
                viewModel.deleteProject(task)
                Snackbar.make(binding.root, "Successfully delete task", Snackbar.LENGTH_SHORT)
                    .apply {
                        show()
                    }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerView)
        }
    }

    fun setupSwipeRefresh() {
        binding.swipe.setColorSchemeResources(
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green
        )
        binding.swipe.setOnRefreshListener {
            viewModel.getAllProjects()
            setupScreen()

        }
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}