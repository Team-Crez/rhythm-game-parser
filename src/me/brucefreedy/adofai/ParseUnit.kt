package me.brucefreedy.adofai

import com.google.gson.JsonObject

data class ParseUnit(
    var bpm: Double,
    var floor: Int = 0,
    val angleList: List<Int>,
    var json: JsonObject,
    var dir: Boolean = true,
)