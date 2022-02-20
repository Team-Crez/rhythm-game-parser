package me.brucefreedy.game

class Level(number: Number) {
    private val int: Number
    init {
        this.int = if (number.toInt() == number) number.toInt() else number
    }

    override fun toString(): String {
        return int.toString()
    }
}

val Float.level get() = Level(this)