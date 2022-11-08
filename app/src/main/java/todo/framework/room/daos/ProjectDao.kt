package todo.framework.room.daos

import androidx.room.Query
import androidx.room.Transaction
import todo.framework.room.entities.ProjectTaskEntity

interface ProjectDao {
    @Transaction
    @Query("SELECT * FROM project_table ")
    fun getProjectWithTask():List<ProjectTaskEntity>
}