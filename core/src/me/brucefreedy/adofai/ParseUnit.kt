package me.brucefreedy.adofai

import com.google.gson.JsonObject
import me.brucefreedy.game.Angle

data class ParseUnit(
    var bpm: Int,
    var floor: Int = 0,
    val angleList: List<Angle>,
    var json: JsonObject,
    var dir: Boolean = true,
)