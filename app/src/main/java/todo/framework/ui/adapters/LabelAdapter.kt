package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TagPickerItemBinding

class LabelAdapter(private val lista: List<String>,private val stringPicked:(string:String)->Unit):RecyclerView.Adapter<LabelAdapter.LabelsViewHolder>() {

   inner class LabelsViewHolder(private val binding:TagPickerItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(string: String) {
            binding.tvLabelName.text = string
            binding.tvLabelName.setOnClickListener {
                stringPicked(string)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelsViewHolder {
       return LabelsViewHolder(
            TagPickerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: LabelsViewHolder, position: Int) {
        holder.bind(lista[position])

    }
}