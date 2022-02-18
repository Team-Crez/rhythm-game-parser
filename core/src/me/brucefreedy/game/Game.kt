package me.brucefreedy.game

interface Game {
    /**
     * input: file
     * output: parsed result
     */
    fun parse(file: String): Result
}