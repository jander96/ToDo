package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.LabelPageBinding
import todo.framework.ui.adapters.ProjectLabelsExpanableAdapter

class LabelFragment: Fragment(R.layout.label_page) {
    private var _binding: LabelPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var expanableListView:  ExpandableListView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LabelPageBinding.bind(view)
        expanableListView = binding.expandableListView
        val titleList = listOf("Projects","Tags")
        val map = hashMapOf(titleList[0] to listOf("Mi Projecto","Tu Projecto","Nuestro Projecto"),
        titleList[1] to listOf(" Tag1","tag 2","tag 3","Tag 4","Tag 5"))
        val adapter = ProjectLabelsExpanableAdapter(requireContext(),titleList,map)
        expanableListView.setAdapter(adapter)
        expanableListView.setOnChildClickListener {
                expandableListView, view, groupPosition, childPosition, l ->
            true
        }


    }
}