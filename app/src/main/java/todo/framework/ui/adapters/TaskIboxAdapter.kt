package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.todo.databinding.RecyclerItemBinding
import todo.framework.Task

class TaskIboxAdapter: ListAdapter<Task,TaskIboxAdapter.InboxViewHolder>(DiffutilCallbackInboxTask){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
       return InboxViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class InboxViewHolder(private val binding:RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task) {
            binding.txtTaskTitle.text = task.content

        }

    }
    private object DiffutilCallbackInboxTask : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task)=
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task)=
            oldItem == newItem
    }
}
