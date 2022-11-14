package todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import todo.framework.ui.views.AddTaskBottomSheet


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var taskBottomSheet: AddTaskBottomSheet

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
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.collapsingToolbar.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
        binding.bottomNavigation.setupWithNavController(navController)

        updateComponentsInNavigation()
        taskBottomSheet = AddTaskBottomSheet()


       binding.fab.setOnClickListener {
          setUpBottomSheet()
       }

    }
    private fun updateComponentsInNavigation(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val background = AppCompatResources.getDrawable(this@MainActivity,R.color.background_page)
            val colorToolbar = AppCompatResources.getDrawable(this@MainActivity,R.color.color_toolbar)

            if (destination.id == R.id.settingsFragment) {
                with(binding) {
                    this.bottomNavigation.visibility = View.INVISIBLE
                    this.bottomAppBar.visibility = View.INVISIBLE
                    this.fab.visibility = View.INVISIBLE
                    searchView.visibility=View.INVISIBLE

                }
            } else {
                with(binding) {
                    this.bottomNavigation.visibility = View.VISIBLE
                    this.bottomAppBar.visibility = View.VISIBLE
                    this.searchView.visibility = View.VISIBLE
                    this.fab.visibility = View.VISIBLE
                    this.appbarLayout.visibility = View.VISIBLE
                    this.collapsingToolbar.visibility = View.VISIBLE

                }
            }
        }
    }
    private fun setUpBottomSheet(){
        val bottomSheetBehavior = (taskBottomSheet.dialog as? BottomSheetDialog)?.behavior
        bottomSheetBehavior?.saveFlags = BottomSheetBehavior.SAVE_ALL
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED

        taskBottomSheet.show(supportFragmentManager,AddTaskBottomSheet.TAG)



    }
}