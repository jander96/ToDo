package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import todo.framework.room.entities.LabelEntity
import todo.framework.room.entities.LabelWithTasks

@Dao
interface LabelDao {
    //Labels

    //Personal labels
    @Query("SELECT * FROM label_table")
    fun getAllPersonalLabels(): Flow<List<LabelEntity>>
    @Query("SELECT id FROM label_table WHERE name = :labelName")
    suspend fun getLabelIdByName(labelName : String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPersonalLabel( label: LabelEntity)

    @Query("SELECT * FROM label_table WHERE id = :idLabel")
    suspend fun getPersonalLabelById( idLabel : String):LabelEntity

    @Update
    suspend fun updatePersonalLabel( label: LabelEntity)

    @Query("DELETE FROM label_table WHERE id = :idLabel")
    suspend fun deleteLabelById(idLabel: String)

    @Query("DELETE FROM label_table")
    suspend fun clearAllLabels()
}