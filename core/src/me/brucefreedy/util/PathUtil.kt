package me.brucefreedy.util

import com.google.gson.JsonObject
import me.brucefreedy.game.Angle
import me.brucefreedy.adofai.Const
import me.brucefreedy.game.angle

object PathUtil {

    /*todo
    * 길 각도 구하기 만들기
    * 길 사이 간격의 시차 리스트 만들기
    *
     */

    val PATH_MAP = mapOf(
        "R" to 0,
        "p" to 15,
        "J" to 30,
        "E" to 45,
        "T" to 60,
        "o" to 75,
        "U" to 90,
        "q" to 105,
        "G" to 120,
        "Q" to 135,
        "H" to 150,
        "W" to 165,
        "L" to 180,
        "x" to 195,
        "N" to 210,
        "Z" to 225,
        "F" to 240,
        "V" to 255,
        "D" to 270,
        "Y" to 285,
        "B" to 300,
        "C" to 315,
        "M" to 330,
        "A" to 345,
        "!" to 999,
    )


    fun calcAngle(jsonObject: JsonObject) =
        if (jsonObject.has(Const.pathData))
        //pathData
            jsonObject.get(Const.pathData).asString.split("").let { it.subList(1, it.size - 1) }
                .map { PATH_MAP[it]!!.angle }.toList()
        else
        //angleData
            jsonObject.get(Const.angleData).asJsonArray.map { it.asBigInteger.toInt().angle }


}
