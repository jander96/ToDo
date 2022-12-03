package todo.framework.ui.views

import android.os.Build
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
import todo.framework.Task
import todo.framework.ui.viewmodels.AddTaskViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddTaskBottomSheet @Inject constructor() : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "task_bottom_sheet"
        const val TASK = "task"
    }

    private var _binding: CreateTaskBottonSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTaskViewModel by viewModels()
    private var id: String = ""
    private var date: String? = null
    private var time: String? = null
    private var labelList: Array<String>? = null
    private var projectId: String? = null
    private var task: Task? = null


    private val datePicker = DatePickerFragment { year, moth, day ->
        date = viewModel.onDateSelected(year, moth, day)
    }
    private val timePicker = TimePickerFragment { hour, minutes ->
        time = viewModel.onTimeSelected(hour, minutes)
    }
    private val labelsPickerBottomSheet = LabelsPickerBottomSheet { labelNamePicked ->
        labelList = viewModel.onLabelSelected(labelNamePicked)
    }
    private val projectPickerBottomSheet = ProjectPickerBottomSheet { projectIdPicked ->
        projectId = viewModel.onProjectSelected(projectIdPicked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task = if (Build.VERSION.SDK_INT >= 33) {
            arguments?.getParcelable(TASK, Task::class.java)
        } else {
            arguments?.getParcelable<Task>(TASK)
        }

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

        fill()

        binding.ivCalendar.setOnClickListener {
            showDatePickerDialog()
        }

        binding.ivClock.setOnClickListener {
            showTimePicker()
        }

        binding.ivTag.setOnClickListener {
            showTagPicker()
        }
        if (task?.projectId != null) binding.ivProject.visibility = View.GONE
        binding.ivProject.setOnClickListener {
            showProjectPicker()
        }


        binding.ivSend.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    binding.tvMessangeError.visibility = View.GONE
                    binding.animationLoading.visibility = View.VISIBLE
                }
                val response = viewModel.createTask(buildTask())
                withContext(Dispatchers.Main) {
                    binding.animationLoading.visibility = View.GONE
                    if (response.data == null) {
                        binding.tvMessangeError.visibility = View.VISIBLE
                    } else {
                        cleanTextFieldAndMemory()
                        dismiss()
                    }
                }

            }
        }
    }

    private fun fill() {
        binding.etTaskName.setText(task?.content ?: "")
        binding.etTaskDescription.setText(task?.description ?: "")
    }


    private fun cleanTextFieldAndMemory() {
        binding.etTaskName.setText("")
        binding.etTaskDescription.setText("")
        id = ""
        date = null
        time = null
        labelList = null
        projectId = null

    }


    private fun buildTask(): Task {
        val id = task?.id ?: id
        val content =
            if (binding.etTaskName.text.toString() != "") binding.etTaskName.text.toString()
            else task?.content ?: ""
        val description =
            if (binding.etTaskDescription.text.toString() != "") binding.etTaskDescription.text.toString()
            else task?.description ?: ""
        val date = date ?: task?.date
        val dateTime = if(date != null && time != null ) date+time else time ?: task?.datetime
        val labels = labelList ?: task?.labels
        val projectId = projectId ?: task?.projectId

        return Task(
            id = id,
            content = content,
            description = description,
            projectId = projectId,
            date = date,
            datetime = dateTime,
            labels = labels
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
