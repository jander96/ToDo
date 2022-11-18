package todo.framework.room.entities

import androidx.room.Embedded
import androidx.room.Relation


data class ProjectTaskRelation(

    @Embedded
    val project : ProjectEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    val task : List<TaskEntity>
)

