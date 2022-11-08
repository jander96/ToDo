package todo.framework.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import todo.framework.room.entities.LabelEntity

@Dao
interface LabelDao {
    //Labels

    //Personal labels
    @Query("SELECT * FROM label_table")
    suspend fun getAllPersonalLabels(): List<LabelEntity>

    @Insert
    suspend fun createPersonalLabel( label: LabelEntity)

    @Query("SELECT * FROM label_table WHERE id = :idLabel")
    suspend fun getPersonalLabelById( idLabel : String)

    @Update
    suspend fun updatePersonalLabel( label: LabelEntity)

    @Delete
    suspend fun deleteLabelById(label: LabelEntity)

    // Shared Labels

    /*suspend fun getAllSharedLabels(): List<String>

    suspend fun renameSharedLabels(@Body renamedLabel: RenamedLabelDto)

    suspend fun  removeSharedLabels(@Body labelName: String)*/
}