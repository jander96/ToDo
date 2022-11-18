package todo.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import todo.framework.room.daos.LabelDao
import todo.framework.room.daos.ProjectDao
import todo.framework.room.daos.TaskDao
import todo.framework.room.entities.Converters
import todo.framework.room.entities.LabelEntity
import todo.framework.room.entities.ProjectEntity
import todo.framework.room.entities.TaskEntity

@Database(entities = [ProjectEntity::class,TaskEntity::class,LabelEntity::class],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TodoDataBase: RoomDatabase() {
    abstract  fun getProjectDao():ProjectDao
    abstract fun getTaskDao():TaskDao
    abstract  fun getLabelDao():LabelDao

    companion object{
        var INSTANCE : TodoDataBase? = null
        fun getDatabase(context: Context):TodoDataBase{
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context,
                    TodoDataBase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}