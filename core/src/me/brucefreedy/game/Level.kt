package me.brucefreedy.game

class Level(int: Number) {
    private val int: Number
    init {
        this.int = if (int.toInt() == int) int.toInt() else int
    }

    override fun toString(): String {
        return int.toString()
    }
}

val Float.level get() = Level(this)