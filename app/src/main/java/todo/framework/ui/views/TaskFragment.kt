package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
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
import todo.framework.ui.adapters.TaskAdapter
import todo.framework.ui.viewmodels.TaskViewModel

@AndroidEntryPoint
class TaskFragment: Fragment(R.layout.task_page) {
    private var _binding: TaskPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner : Spinner
    



    private val viewModel: TaskViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= TaskPageBinding.bind(view)
        navController = findNavController()

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.collapsingToolbar.setupWithNavController(binding.toolbar,navController,appBarConfiguration)
        binding.toolbar.inflateMenu(R.menu.main_menu)


        recyclerView = binding.recyclerView
        spinner = binding.spinner

        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter

        recyclerView.layoutManager = linearLayoutManager

        lifecycleScope.launch(Dispatchers.Main){
            viewModel.listaTask.collect{
                adapter.submitList(it)
            }
        }



    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}