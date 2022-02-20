package me.brucefreedy.adofai

interface ActionEvent {
    fun parse(parseUnit: ParseUnit)
}

class SetSpeed : ActionEvent {
    override fun parse(pu: ParseUnit) {
        when (pu.json.get(Const.speedType).asString) {
            "Multiplier" -> pu.bpm *= pu.json.get(Const.bpmMultiplier).asDouble
            "Bpm" -> pu.bpm = pu.json.get(Const.beatsPerMinute).asDouble
        }
    }
}

class Twirl : ActionEvent {
    override fun parse(parseUnit: ParseUnit) {
        parseUnit.dir = !parseUnit.dir
    }
}