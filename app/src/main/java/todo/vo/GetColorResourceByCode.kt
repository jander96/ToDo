package todo.vo

import com.example.todo.R

object GetColorResourceByCode {
    fun getResource(colorCode: String): Int{
        return when(colorCode){
            "berry_red"-> R.color.berry_red
            "red"-> R.color.red
            "orange"-> R.color.orange
            "yellow"-> R.color.yellow
            "olive_green"-> R.color.olive_green
            "lime_green"-> R.color.lime_green
            "green"-> R.color.green
            "mint_green"-> R.color.mint_green
            "teal"-> R.color.teal
            "sky_blue"-> R.color.sky_blue
            "light_blue"-> R.color.light_blue
            "blue"-> R.color.blue
            "grape"-> R.color.grape
            "violet"-> R.color.violet
            "lavender"-> R.color.lavender
            "magenta"-> R.color.magenta
            "salmon"-> R.color.salmon
            "charcoal"-> R.color.charcoal
            "grey"-> R.color.grey
            else -> R.color.taupe
        }
    }
}