package me.brucefreedy.adofai

interface ActionEvent {
    fun parse(parseUnit: ParseUnit)
}

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

class Twirl : ActionEvent {
    override fun parse(parseUnit: ParseUnit) {
        parseUnit.dir = !parseUnit.dir
    }
}