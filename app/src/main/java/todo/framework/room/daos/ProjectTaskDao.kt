package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import todo.framework.room.entities.ProjectTaskRelation

@Dao
interface ProjectTaskDao {
    @Transaction
    @Query("SELECT * FROM project_table")
    suspend fun getProjectWhitTask():List<ProjectTaskRelation>
}