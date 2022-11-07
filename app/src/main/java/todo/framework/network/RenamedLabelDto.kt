package todo.framework.network

import com.squareup.moshi.Json

data class RenamedLabelDto(val name:String, @Json(name ="new_name")val newName : String)
