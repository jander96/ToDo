package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.todo.databinding.RecyclerItemBinding

class TaskIboxAdapter(private val lista: List<String>): RecyclerView.Adapter<TaskIboxAdapter.InboxViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
       return InboxViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return lista.size
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        holder.bind(lista[position])
    }


    class InboxViewHolder(private val binding:RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(string:String) {
            binding.txtTaskTitle.text = string

        }

    }
}
