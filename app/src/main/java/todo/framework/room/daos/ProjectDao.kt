package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import todo.framework.room.entities.ProjectEntity
import todo.framework.room.entities.ProjectTaskRelation

@Dao

interface ProjectDao {


    //Projects
    @Query("SELECT * FROM project_table")
    suspend fun getAllProjects(): List<ProjectEntity>

    @Insert
    suspend fun createProject( projectEntity: ProjectEntity)

    @Query("SELECT * FROM project_table WHERE id = :idProject")
    suspend fun getProjectById( idProject: String): ProjectEntity

    @Update
    suspend fun updateProject( projectEntity: ProjectEntity)

   /* @Query("SELECT * FROM project_table ")
    suspend fun getAllProjectCollaborators(@Path("id") idProject: String): List<CollaboratorsDto>*/
}