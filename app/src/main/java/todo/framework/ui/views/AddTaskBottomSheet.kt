package todo.framework.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todo.R
import com.example.todo.databinding.CreateTaskBottonSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddTaskBottomSheet: BottomSheetDialogFragment() {
    private var _binding: CreateTaskBottonSheetBinding? = null
    private val binding get()= _binding!!
    private val datePicker = DatePickerFragment{year,moth,day ->
        onDateSelected(year,moth,day)
    }
    private val timePicker = TimePickerFragment{hour,minutes ->
        onTimeSelected(hour,minutes)
    }



    companion object {
        const val TAG = "task_bottom_sheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_task_botton_sheet,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CreateTaskBottonSheetBinding.bind(view)
        binding.ivCalendar.setOnClickListener {
            Log.d("sheet","Se escucho el evento del click")
            showDatePickerDialog()

        }
        binding.ivClock.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showTimePicker() {
        timePicker.show(requireActivity().supportFragmentManager,"timePicker")
    }

    private fun showDatePickerDialog() {
        datePicker.show(requireActivity().supportFragmentManager,"datePicker")
    }

    private fun  onDateSelected(year:Int,month:Int,day:Int){

    }
    private fun onTimeSelected(hour: Int, minutes: Int) {

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
