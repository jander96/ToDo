package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.LabelPickerPageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import todo.framework.ui.adapters.LabelAdapter

class LabelsPickerBottomSheet(private val labelPicked:(label:String)->Unit ):BottomSheetDialogFragment(R.layout.label_picker_page) {
    private var _binding:LabelPickerPageBinding? = null
    private val binding get()= _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController

    private val lista = listOf("Projecto_familia","Projecto_casa","Projecto_toDo","Projecto_Bodybuilding")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= LabelPickerPageBinding.bind(view)
        navController = findNavController()
        recyclerView= binding.recyclerView
        val adapter = LabelAdapter(lista){stringPicked->
            labelPicked(stringPicked)
        }
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager


        binding.ivAddLabel.setOnClickListener {
            val labelCreated = binding.etNewLabel.text.toString()
            if(labelCreated != ""){
                labelPicked(labelCreated)
            }
        }
    }
}