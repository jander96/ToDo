package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import todo.framework.room.entities.LabelWithTasks

@Dao
interface LabelWithTasksDao {
    @Transaction
    @Query("SELECT * FROM label_table")
    suspend fun getLabelWithTask():List<LabelWithTasks>
}