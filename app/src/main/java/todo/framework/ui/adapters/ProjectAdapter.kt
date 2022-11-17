package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ProjectPickerItemBinding


class ProjectAdapter(private val lista: List<String>, private val projectPicked:(projectPicked:String)->Unit): RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

   inner class ProjectViewHolder(private val binding: ProjectPickerItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(string: String) {
            binding.tvLabelName.text = string
            binding.tvLabelName.setOnClickListener {
                projectPicked(string)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            ProjectPickerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(lista[position])

    }
}