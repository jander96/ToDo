package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.RecyclerItemBinding
import com.example.todo.databinding.TaskPageBinding
import todo.framework.Task


class TaskAdapter : ListAdapter<Task,TaskAdapter.TaskViewHolder>(DiffUtilCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       return TaskViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class TaskViewHolder(private val binding:RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(task:Task) {
            binding.txtTaskTitle.text = task.content

        }

    }
}
private object DiffUtilCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return (oldItem == newItem)
    }


}
