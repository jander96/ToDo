package todo.framework.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="project_table")
data class ProjectEntity(
    @PrimaryKey val id: String,
    val name: String,
    val color: String,
    @ColumnInfo(name = "parent_id") val parentId: String,
    val order: Int,
    @ColumnInfo(name = "comment_count") val commentCount: Int,
    @ColumnInfo(name = "is_shared") val isShared: Boolean,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "is_inbox_project") val isInboxProject: Boolean,
    @ColumnInfo(name = "is_team_inbox") val isTeamInbox: Boolean,
    @ColumnInfo(name = "view_style") val viewStyle: String,
    val url: String
)
