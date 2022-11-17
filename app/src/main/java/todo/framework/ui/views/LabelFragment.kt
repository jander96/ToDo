package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.LabelPageBinding
import todo.framework.ui.adapters.ProjectLabelsExpanableAdapter

class LabelFragment: Fragment(R.layout.label_page) {
    private var _binding: LabelPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var expanableListView:  ExpandableListView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LabelPageBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.toolbar.inflateMenu(R.menu.main_menu)
       setupExpnableListView()


        expanableListView.setOnGroupClickListener { expandableListView, view, i, l ->
            view.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
                if(i ==0){
                    navController.navigate(R.id.action_label_to_addProject)
                }else navController.navigate(R.id.action_label_to_addLabel)
            }
           false
        }





    }
    private fun childClick(){
        expanableListView.setOnChildClickListener {
                expandableListView, view, groupPosition, childPosition, l ->
            true
        }
    }
   private fun setupExpnableListView(){
        expanableListView = binding.expandableListView
        val titleList = listOf("Projects","Tags")
        val map = hashMapOf(titleList[0] to listOf("Mi Projecto","Tu Projecto","Nuestro Projecto"),
            titleList[1] to listOf(" Tag1","tag 2","tag 3","Tag 4","Tag 5"))
        val adapter = ProjectLabelsExpanableAdapter(requireContext(),titleList,map)
        expanableListView.setAdapter(adapter)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}