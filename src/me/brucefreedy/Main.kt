package me.brucefreedy

import me.brucefreedy.game.GameType

object Main {
    fun main(args: Array<String>) = println(GameType.toType(args[0]).parser.parse(args[1]).level)
}
