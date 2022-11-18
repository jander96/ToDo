package todo.framework.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["task_id", "label_id"])
data class TasksLabelsCrossRef(
    @ColumnInfo(name = "task_id") val taskId: String,
    @ColumnInfo(name = "label_id") val labelId: String
)
