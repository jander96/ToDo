package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TagPickerItemBinding
import todo.framework.Label
import todo.framework.Project

class LabelAdapter :ListAdapter<Label,LabelAdapter.LabelsViewHolder>(DiffUtillCallbackLabels) {
    private var _listLabelsPicked: MutableList<String> = mutableListOf()
    val listLabelsPicked : MutableList<String> = _listLabelsPicked
   inner class LabelsViewHolder(private val binding:TagPickerItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(label: Label) {
            binding.tvLabelName.text = label.name
            binding.checkBox.setOnClickListener {
                if(binding.checkBox.isChecked){
                    _listLabelsPicked.add(label.name)
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelsViewHolder {
       return LabelsViewHolder(
            TagPickerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }



    override fun onBindViewHolder(holder: LabelsViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
}
private object DiffUtillCallbackLabels : DiffUtil.ItemCallback<Label>(){
    override fun areItemsTheSame(oldItem: Label, newItem: Label): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Label, newItem: Label): Boolean =
        oldItem == newItem
}