package todo.framework.room.entities

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun arrayToString(array:Array<String>):String{
        var newString = ""
        for(string in array){
            newString += "$string/"
        }
        return newString
    }
    @TypeConverter
    fun stringToArray(string:String?):Array<String>{
        var array:Array<String> = arrayOf()
        var stringFormado = ""
        if (string != null) {
            for(caracter in string){
                if (caracter != '/'){
                    stringFormado+=caracter
                }else{
                    array+=stringFormado
                    stringFormado = ""
                }
            }
        }
        return array
    }

}
