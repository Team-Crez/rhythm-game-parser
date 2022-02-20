package me.brucefreedy.adofai

import com.google.gson.JsonObject
import me.brucefreedy.adofai.Const.actions
import me.brucefreedy.adofai.Const.bpm
import me.brucefreedy.adofai.Const.countDownTicks
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

    private val PATH_MAP = mapOf(
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
            bpm = jsonObject.get(settings).asJsonObject.get(bpm).asDouble,
            angleList = angleList,
            json = jsonObject,
            dir = false
        )

        val eventRegister = mapOf(
            "Twirl" to Twirl(),
            "SetSpeed" to SetSpeed(),
        )
        val list = ArrayList<Int>()
        println(angleList.map { it.number })
        val toList = jsonObject.get(actions).asJsonArray.map { it.asJsonObject }.toList()
        angleList.map { it.number }.forEachIndexed { index, floor ->
            toList.filter{it.get(Const.floor).asInt == index}.forEach {
                val eventType = it.get(Const.eventType).asString
                println(eventType)
                parseUnit.floor = floor
                parseUnit.json = it.asJsonObject
                eventRegister[eventType]?.parse(parseUnit)
            }
            val before = angleList[index].number
            val after = if (index + 1 < angleList.size) angleList[index + 1].number else 0
            var angle = before - after + 180
            if (parseUnit.dir) angle = 360 - angle
            angle %= 360
            val delay = toMillisecond(angle.toDouble(), parseUnit.bpm)
            list.add(delay.toInt())
        }
        return list
    }

    fun toMillisecond(degree: Double, bpm: Double) = (degree / 180.0) * (60.0 / bpm) * 1000.0

    fun getBpm(jsonObject: JsonObject) = jsonObject.get(settings).asJsonObject.get(bpm).asDouble
    fun getCountDownTick(jsonObject: JsonObject) = jsonObject.get(settings).asJsonObject.get(countDownTicks).asInt

}
