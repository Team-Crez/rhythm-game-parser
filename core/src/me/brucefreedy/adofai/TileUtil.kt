package me.brucefreedy.adofai

import com.google.gson.JsonObject
import me.brucefreedy.adofai.Const.actions
import me.brucefreedy.adofai.Const.bpm
import me.brucefreedy.adofai.Const.settings
import me.brucefreedy.adofai.actionevent.SetSpeed
import me.brucefreedy.adofai.actionevent.Twirl
import me.brucefreedy.game.angle

object TileUtil {

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


    private fun calcAngle(jsonObject: JsonObject) =
        if (jsonObject.has(Const.pathData))
        //pathData
            jsonObject.get(Const.pathData).asString.split("").let { it.subList(1, it.size - 1) }
                .map { PATH_MAP[it]!!.angle }.toList()
        else
        //angleData
            jsonObject.get(Const.angleData).asJsonArray.map { it.asBigInteger.toInt().angle }

    fun calcDelay(jsonObject: JsonObject): List<Int> {
        val angleList = calcAngle(jsonObject)
        val parseUnit = ParseUnit(
            bpm = jsonObject.get(settings).asJsonObject.get(bpm).asInt,
            angleList = angleList,
            json = jsonObject,
        )

        val eventRegister =  mapOf(
            "Twirl" to Twirl(),
            "SetSpeed" to SetSpeed(),
        )
        val list = ArrayList<Int>()
        var lastFloor = 0
        jsonObject.get(actions).asJsonArray.map { it.asJsonObject }
            .forEach {
                try {
                    val floor = it.get(Const.floor).asInt
                    val eventType = it.get(Const.eventType).asString
                    parseUnit.floor = floor
                    parseUnit.json = it.asJsonObject
                    eventRegister[eventType]?.parse(parseUnit)
                    if (lastFloor != floor) {
                        lastFloor = floor
                        val before = angleList[floor - 1].number
                        val current = angleList[floor].number + if (parseUnit.dir) 180 else 0
                        val delay = ((current - before) / 360.0) * (parseUnit.bpm / 60.0) * 1000.0
                        list.add(delay.toInt())
                    }
                    println(floor)
                } catch (_: Exception) {
                    list.add(0)
                }
            }
        return list
    }

}
