package todo

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import todo.framework.network.LabelDto
import todo.framework.network.ProjectDto
import todo.framework.network.TaskDto
import todo.framework.network.ToDoApiServices
import todo.framework.ui.views.AddTaskBottomSheet
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
   @Inject
   lateinit var taskBottomSheet: AddTaskBottomSheet
   @Inject
   lateinit var retrofitToDoApiServices: ToDoApiServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.bottomNavigation) {
            background = null
            menu.getItem(2).isEnabled = false
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
        }



       binding.fab.setOnClickListener {
          setUpBottomSheet()
       }


    }


    private fun setUpBottomSheet(){
        val bottomSheetBehavior = (taskBottomSheet.dialog as? BottomSheetDialog)?.behavior
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        taskBottomSheet.show(supportFragmentManager,AddTaskBottomSheet.TAG)

    }

}