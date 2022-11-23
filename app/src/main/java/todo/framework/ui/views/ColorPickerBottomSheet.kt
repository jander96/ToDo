package todo.framework.ui.views


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ColorPickerBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import todo.framework.ui.adapters.ColorsPickerAdapter

class ColorPickerBottomSheet(private val getColorCode:(colorCode:String)->Unit):BottomSheetDialogFragment(R.layout.color_picker_bottom_sheet) {
    private var _binding: ColorPickerBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val adapter = ColorsPickerAdapter{ colorCode, isDestroyed ->
        getColorCode(colorCode)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ColorPickerBottomSheetBinding.bind(view)
        setupRecyclerView()

    }
    private fun setupRecyclerView(){
       recyclerView = binding.recyclerView
        val layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

}