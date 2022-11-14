package todo.framework.ui.views

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment(private val listener: (hour: Int, minutes: Int) -> Unit) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onTimeSet(p0: TimePicker?, hour: Int, minutes: Int) {
        listener(hour,minutes)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minutes = c.get(Calendar.MINUTE)
        return TimePickerDialog(requireContext(),this,hour,minutes,true)
    }
}