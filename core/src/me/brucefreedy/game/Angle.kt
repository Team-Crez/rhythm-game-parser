package me.brucefreedy.game

class Angle(val int: Int)

val Int.angle get() = Angle(this)