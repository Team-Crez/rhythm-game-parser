package me.brucefreedy.adofai.actionevent

import me.brucefreedy.adofai.ParseUnit

interface ActionEvent {
    fun parse(parseUnit: ParseUnit)
}