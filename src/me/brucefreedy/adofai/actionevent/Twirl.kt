package me.brucefreedy.adofai.actionevent

import me.brucefreedy.adofai.ParseUnit

class Twirl : ActionEvent {
    override fun parse(parseUnit: ParseUnit) {
        parseUnit.dir = !parseUnit.dir
    }
}