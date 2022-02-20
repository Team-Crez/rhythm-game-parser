package me.brucefreedy.adofai.actionevent

import me.brucefreedy.adofai.Const
import me.brucefreedy.adofai.ParseUnit

class SetSpeed : ActionEvent {
    override fun parse(parseUnit: ParseUnit) {
        val json = parseUnit.json
        when (json.get(Const.speedType).asString) {
            "Multiplier" -> parseUnit.bpm *= json.get(Const.bpmMultiplier).asDouble
            "Bpm" -> parseUnit.bpm = json.get(Const.beatsPerMinute).asDouble
        }
        println("now ${parseUnit.bpm} bpm")
    }
}