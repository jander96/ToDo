package todo.framework.ui.views

import android.os.Bundle
import android.util.Log
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
import todo.domain.DueDomain
import todo.domain.ResponseState
import todo.framework.Task
import todo.framework.ui.viewmodels.AddTaskViewModel
import javax.inject.Inject
@AndroidEntryPoint
class AddTaskBottomSheet @Inject constructor()  : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "task_bottom_sheet"
    }

    private var _binding: CreateTaskBottonSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTaskViewModel by viewModels()
    private var date: String? = null
    private var dateTime: String? = null
    private var labelName: String? = null
    private var projectName: String? = null


    private val datePicker = DatePickerFragment { year, moth, day ->
        date = viewModel.onDateSelected(year, moth, day)
    }
    private val timePicker = TimePickerFragment { hour, minutes ->
        dateTime = viewModel.onTimeSelected(hour, minutes)
    }
    private val labelsPickerBottomSheet = LabelsPickerBottomSheet { labelPicked ->
        labelName = viewModel.onLabelSelected(labelPicked)
    }
    private val projectPickerBottomSheet = ProjectPickerBottomSheet { projectPicked ->
        projectName = viewModel.onProjectSelected(projectPicked)
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
            checkIfEmptyForm()
            buildTask()
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.taskBuilded.collect{task->
                    if(task != null){
                        viewModel.createTask(task)
                    }
                }

            }
            lifecycleScope.launch {
                viewModel.responseState.collect{
                    when(it){
                        is ResponseState.Success->{}
                        is ResponseState.Error ->{}
                        is ResponseState.Loading->{}
                    }
                }
            }

        }
    }

    private fun checkIfEmptyForm() {

    }

    private fun buildTask() {
        val content = binding.etTaskName.text.toString()
        val description = binding.etTaskDescription.text.toString()
        val date = date
        val dateTime = dateTime
        val labelName = labelName
        val projectName = projectName
        val due = DueDomain(date = date, datetime = date + dateTime)

        Log.d("Due","El objeto due tien los valores ${due.date}, ${due.datetime}")
        viewModel.buildTask(
            content,description,due,labelName,projectName
        )

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
