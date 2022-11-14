package todo.framework.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddTaskBottomSheet: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_task_botton_sheet,container,false)
    }
    companion object {
        const val TAG = "task_bottom_sheet"
    }

}
