package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ProjectPickerItemBinding
import todo.framework.Project


class ProjectAdapter(private val projectIdPicked:(string:String)->Unit) : ListAdapter<Project,ProjectAdapter.ProjectViewHolder>(DiffUtillCallbackProjects) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            ProjectPickerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
   inner class ProjectViewHolder(private val binding: ProjectPickerItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(project :Project ) {
            binding.tvLabelName.text = project.name
            binding.tvLabelName.setOnClickListener {
                projectIdPicked(project.id)
            }
        }


    }
}
private object DiffUtillCallbackProjects :DiffUtil.ItemCallback<Project>(){
    override fun areItemsTheSame(oldItem: Project, newItem: Project) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Project, newItem: Project)=
        oldItem == newItem
}
