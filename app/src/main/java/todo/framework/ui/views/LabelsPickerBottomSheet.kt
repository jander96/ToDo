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
class LabelsPickerBottomSheet(private val labelPicked: (labels: Array<String>) -> Unit) :
    BottomSheetDialogFragment(R.layout.label_picker_page) {
    private var _binding: LabelPickerPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private val viewModel: LabelPickerViewModel by viewModels()
    private val adapter = LabelAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LabelPickerPageBinding.bind(view)
        navController = findNavController()
        recyclerView = binding.recyclerView
        setupScreen()


        binding.ivOk.setOnClickListener {
            val array = Array(adapter.listLabelsPicked.size) { "" }

            for (index in adapter.listLabelsPicked.indices) {
                array[index] = adapter.listLabelsPicked[index]
            }
            labelPicked(array)
            dismiss()
        }
        binding.ivRetry.setOnClickListener {
            viewModel.getListOfLabels()
            setupScreen()
        }


    }

    private fun setupScreen() {
        lifecycleScope.launch {
            viewModel.listOfLabels.collect { response ->
                when (response) {
                    is ResponseState.Error -> {
                        showRetryIcon()

                    }

                    is ResponseState.Loading -> {
                        showLoadingBar()
                    }

                    is ResponseState.Success -> {
                        response.data?.collect { listLabel ->
                            setupRecyclerView(listLabel)
                            binding.ivRetry.visibility = View.INVISIBLE
                            binding.progressBar.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
    }



    private fun showRetryIcon() {
        binding.ivRetry.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showLoadingBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.ivRetry.visibility = View.INVISIBLE
    }

    private fun setupRecyclerView(listLabel: List<Label>) {

        adapter.submitList(listLabel)
        recyclerView.adapter = adapter
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }
}