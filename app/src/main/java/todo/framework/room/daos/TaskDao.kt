package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import todo.framework.room.entities.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table Where content Like :query ")
    fun searchTask(query:String):Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewTask( task: TaskEntity)

    @Query("SELECT * FROM task_table WHERE id = :idTask")
    suspend fun getAnActiveTaskById( idTask:String):TaskEntity

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM task_table WHERE id = :idTask")
    suspend fun deleteTask(idTask: String )

    @Query("DELETE FROM task_table")
    suspend fun clearAllTaskInDB()



}