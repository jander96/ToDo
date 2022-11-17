package todo.framework.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import com.example.todo.R
import com.example.todo.databinding.CreateTaskBottonSheetBinding

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import todo.framework.ui.viewmodels.AddTaskViewModel
import javax.inject.Inject

class AddTaskBottomSheet @Inject constructor()  : BottomSheetDialogFragment() {
    private var _binding: CreateTaskBottonSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTaskViewModel by viewModels()


    private val datePicker = DatePickerFragment { year, moth, day ->
        viewModel.onDateSelected(year, moth, day)
    }
    private val timePicker = TimePickerFragment { hour, minutes ->
        viewModel.onTimeSelected(hour, minutes)
    }
    private val labelsPickerBottomSheet = LabelsPickerBottomSheet { labelPicked ->
        viewModel.onLabelSelected(labelPicked)
    }
    private val projectPickerBottomSheet = ProjectPickerBottomSheet { projectPicked ->
        viewModel.onProjectSelected(projectPicked)
    }


    companion object {
        const val TAG = "task_bottom_sheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_task_botton_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CreateTaskBottonSheetBinding.bind(view)


        binding.ivCalendar.setOnClickListener {
            showDatePickerDialog()
        }

        binding.ivClock.setOnClickListener {
            showTimePicker()
        }

        binding.ivTag.setOnClickListener {
            showTagPicker()
        }
        binding.ivProject.setOnClickListener {
            showProjectPicker()
        }
    }


    private fun showTimePicker() {
        timePicker.show(requireActivity().supportFragmentManager, "timePicker")
    }

    private fun showDatePickerDialog() {
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")
    }

    private fun showTagPicker() {

        labelsPickerBottomSheet.show(requireActivity().supportFragmentManager, "labelPicker")
    }

    private fun showProjectPicker() {
        projectPickerBottomSheet.show(requireActivity().supportFragmentManager, "projectPicker")
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
