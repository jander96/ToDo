package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import todo.framework.room.entities.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    suspend fun getActiveTasks(): List<TaskEntity>

    @Insert
    suspend fun createNewTask( task: TaskEntity)

    @Query("SELECT * FROM task_table WHERE id = :idTask")
    suspend fun getAnActiveTaskById( idTask:String)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity )


   /* suspend fun closeTask(@Path("id") idTask: String)

    suspend fun reopenTask(@Path("id") idTask: String)

    */
}