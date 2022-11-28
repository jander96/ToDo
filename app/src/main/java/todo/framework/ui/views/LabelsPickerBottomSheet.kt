package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.LabelPickerPageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import todo.domain.ResponseState
import todo.framework.Label
import todo.framework.ui.adapters.LabelAdapter
import todo.framework.ui.viewmodels.LabelPickerViewModel

@AndroidEntryPoint
class LabelsPickerBottomSheet(private val labelPicked:(label:String)->Unit ):BottomSheetDialogFragment(R.layout.label_picker_page) {
    private var _binding:LabelPickerPageBinding? = null
    private val binding get()= _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private val viewModel : LabelPickerViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= LabelPickerPageBinding.bind(view)
        navController = findNavController()
        recyclerView= binding.recyclerView
        lifecycleScope.launch {
            viewModel.listOfLabels.collect{response->
                when(response){
                    is ResponseState.Error -> {

                    }
                    is ResponseState.Loading -> {

                    }
                    is ResponseState.Success -> {
                        response.data?.collect{listLabel->
                            setupRecyclerView(listLabel)
                        }
                    }
                }
            }
        }


    }
    private fun setupRecyclerView(listLabel: List<Label>){

        val adapter = LabelAdapter{
            labelPicked(it)
            dismiss()
        }.apply {
            submitList(listLabel)
        }

        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
    }
}