package me.brucefreedy.adofai

import me.brucefreedy.game.Game
import me.brucefreedy.game.Level
import me.brucefreedy.game.Result
import me.brucefreedy.game.level
import me.brucefreedy.util.StringUtil

class Adofai : Game {

    override fun parse(file: String): Result {
        //parse file to json object
        val jsonObject = StringUtil.parseJsonFile(file)

        //parse tile from json object
        val calcDelay = TileUtil.calcDelay(jsonObject)
        println(calcDelay)

        return AdofaiResult(1f.level, calcDelay, TileUtil.getBpm(jsonObject), TileUtil.getCountdownTicks(jsonObject))
    }
}

class AdofaiResult(
    override val level: Level,
    val calcDelay: List<Int>,
    val startBpm: Double,
    val countDownTick: Int,
) : Result