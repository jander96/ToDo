package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ColorPickerItemBinding
import todo.domain.Colors
import todo.framework.ColorsResource

class ColorsPickerAdapter(private val getColorCode: (colorCode:String,isDestroyed:Boolean)->Unit):RecyclerView.Adapter<ColorsPickerAdapter.ColorsViewHolder>() {
    private val listOfColors = ColorsResource.listOfColors
   class ColorsViewHolder(private val binding: ColorPickerItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Colors,getColorCode: (colorCode:String,isDestroyed:Boolean)->Unit ){
            binding.item.setOnClickListener {
                getColorCode(color.codeName,true)
            }

            binding.tvColorName.text = color.colorName
            binding.ivColor.background = AppCompatResources.getDrawable(binding.item.context,color.colorResource)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
       return ColorsViewHolder(
           ColorPickerItemBinding
               .inflate(LayoutInflater.from(parent.context),parent,false)
       )
    }

    override fun getItemCount(): Int =
        listOfColors.size


    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        holder.bind(listOfColors[position],getColorCode)

    }
}