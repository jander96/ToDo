package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import todo.framework.room.entities.ProjectEntity

@Dao

interface ProjectDao {


    //Projects
    @Query("SELECT * FROM project_table")
    fun getAllProjects(): Flow<List<ProjectEntity>>
    @Query("SELECT id From project_table Where name = :projectName")
    suspend fun findProjectIdByName( projectName : String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProject( vararg projectEntity: ProjectEntity)

    @Query("SELECT * FROM project_table WHERE id = :idProject")
    suspend fun getProjectById( idProject: String): ProjectEntity

    @Update
    suspend fun updateProject( projectEntity: ProjectEntity)

   @Query("DELETE FROM project_table WHERE id = :idProject")
   suspend fun deleteProject(idProject : String)
   @Query("DELETE FROM project_table")
   suspend fun clearAllProjectInDB()
}