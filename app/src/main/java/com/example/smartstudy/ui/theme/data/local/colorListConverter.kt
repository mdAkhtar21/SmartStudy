package com.example.smartstudy.ui.theme.data.local

import androidx.room.TypeConverter

class colorListConverter {
    @TypeConverter
   fun fromColorList(colorList:List<Int>):String{
        return colorList.joinToString(","){
            it.toString()
        }
    }
    @TypeConverter

    fun toColorList(colorListString:String):List<Int>{
        return colorListString.split(",").map { it.toInt() }
    }

}