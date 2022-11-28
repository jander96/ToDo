package todo.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R

import com.example.todo.databinding.RecyclerProjectItemBinding
import todo.framework.Project
import todo.vo.GetColorResourceByCode

class ProjectHomeAdapter(private val projectPicked: (projectId: String) -> Unit) :
    ListAdapter<Project, ProjectHomeAdapter.InboxViewHolder>(DiffutilCallbackInboxTask) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        return InboxViewHolder(
            RecyclerProjectItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        holder.bind(getItem(position), projectPicked)
    }

    class InboxViewHolder(private val binding: RecyclerProjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project, projectPicked: (projectId: String) -> Unit) {
            if (project.color != null) {
                val color = GetColorResourceByCode.getResource(project.color)
                binding.viewProjectColor.background =
                    AppCompatResources.getDrawable(binding.root.context, color)
            }

            binding.tvProjectName.text = project.name
            binding.layout.setOnClickListener {
                projectPicked(project.id)
            }
            if (project.isFavorite != null && project.isFavorite) {
                binding.ivFavorite.setImageDrawable(
                    AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.favorite_svgrepo_com
                    )
                )
            } else binding.ivFavorite.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    R.drawable.not_favorite
                )
            )

        }

    }

    private object DiffutilCallbackInboxTask : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean =
            oldItem == newItem
    }
}
