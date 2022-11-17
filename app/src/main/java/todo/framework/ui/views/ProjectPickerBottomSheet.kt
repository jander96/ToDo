package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ProjectPickerPageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import todo.framework.ui.adapters.ProjectAdapter

class ProjectPickerBottomSheet(private val projectPicked: (projectPicked: String) -> Unit) :
    BottomSheetDialogFragment(R.layout.project_picker_page) {
    private var _binding: ProjectPickerPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController

    private val lista = listOf(
        "Projecto_Feliz",
        "Projecto_casi terminado",
        "Projecto_Listo",
        "Projecto_Bodybuilding"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ProjectPickerPageBinding.bind(view)
        navController = findNavController()
        recyclerView = binding.recyclerView
        val adapter = ProjectAdapter(lista) { projectPicked ->
            projectPicked(projectPicked)
        }
        recyclerView.adapter = adapter
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        binding.ivAddLabel.setOnClickListener {
            val projectCreated = binding.etNewLabel.text.toString()
            if (projectCreated != "") {
                projectPicked(projectCreated)
            }
        }
    }
}