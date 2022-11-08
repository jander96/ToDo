package todo.framework.room.entities

import androidx.room.Embedded
import androidx.room.Relation


data class ProjectTaskEntity(
    @Embedded
    val projectEntity: ProjectEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    val taskEntity: List<TaskEntity>
)
