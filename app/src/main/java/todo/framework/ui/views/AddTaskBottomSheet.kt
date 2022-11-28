package todo.framework.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.todo.R
import com.example.todo.databinding.CreateTaskBottonSheetBinding

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import todo.domain.DueDomain
import todo.domain.ResponseState
import todo.framework.Task
import todo.framework.ui.viewmodels.AddTaskViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddTaskBottomSheet @Inject constructor() : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "task_bottom_sheet"
    }

    private var _binding: CreateTaskBottonSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTaskViewModel by viewModels()
    private var date: String? = null
    private var dateTime: String? = null
    private var labelName: String? = null
    private var projectId: String? = null


    private val datePicker = DatePickerFragment { year, moth, day ->
        date = viewModel.onDateSelected(year, moth, day)
    }
    private val timePicker = TimePickerFragment { hour, minutes ->
        dateTime = viewModel.onTimeSelected(hour, minutes)
    }
    private val labelsPickerBottomSheet = LabelsPickerBottomSheet { labelNamePicked ->
        labelName = viewModel.onLabelSelected(labelNamePicked)
    }
    private val projectPickerBottomSheet = ProjectPickerBottomSheet { projectIdPicked ->
        projectId = viewModel.onProjectSelected(projectIdPicked)
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


        binding.ivSend.setOnClickListener {

           lifecycleScope.launchWhenResumed {
               withContext(Dispatchers.IO){ viewModel.createTask(buildTask())}
           }

        }
    }


    private fun buildTask(): Task {
        val content = binding.etTaskName.text.toString()
        val description = binding.etTaskDescription.text.toString()
        val date = date
        val dateTime = dateTime
        val labelName = labelName
        val projectId = projectId
        val due = DueDomain(date = date, datetime = date + dateTime)

        return Task(
            id = "",
            content = content,
            description = description,
            projectId = projectId,
            labels = arrayOf(labelName)// Cambiar esta implementacion hacer un adapter q tenga un check box
                                       // y mandar desde el un array con todos los  items con check box seleccionados
        )                              // tambien tener en cuenta de no crear un array de null , si este es el caso entences
                                        // se debe pasar label = null en vez de label = arrayOf(null)

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
