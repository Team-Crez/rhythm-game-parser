package me.brucefreedy.adofai

import me.brucefreedy.game.Game
import me.brucefreedy.game.Level
import me.brucefreedy.game.Result
import me.brucefreedy.game.level
import me.brucefreedy.util.PathUtil
import me.brucefreedy.util.StringUtil

class Adofai : Game {
    override fun parse(file: String): Result {
        //parse file to json object
        val jsonObject = StringUtil.parseJsonFile(file)

        //parse tile angle from json object
        PathUtil.calcAngle(jsonObject).toString()

        return AdofaiResult(1f.level)
    }
}

class AdofaiResult(override val level: Level) : Result