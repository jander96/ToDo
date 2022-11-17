package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TaskPageBinding


class TaskAdapter(private val lista: List<String>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       return TaskViewHolder(
            TaskPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return lista.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(lista[position])
    }


    class TaskViewHolder(private val binding:TaskPageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(s: String) {


        }

    }
}
