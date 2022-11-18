package todo.framework.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class LabelWithTasks(
    @Embedded
    val label: LabelEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        associateBy = Junction(TasksLabelsCrossRef::class)
    )
    val tasks : List<TaskEntity>
)
