package me.brucefreedy.game

import me.brucefreedy.adofai.Adofai

enum class GameType {  //게임 종류 enum
    A_DANCE_OF_FIRE_AND_ICE,
    RHYTHM_DOCTOR
    ;
    val parser get() =
        when(this) {
            A_DANCE_OF_FIRE_AND_ICE -> Adofai()
            RHYTHM_DOCTOR -> TODO("Not implemented yet")
        }
    companion object {
        /**
         * 문자열을 GameType enum 으로 반환 합니다
         * 0 일 경우 ADOFAI, 1 일 경우 R/D
         * 또는 해당 enum 에 맞는 name 일 경우
         */
        fun toType(str: String): GameType {  //문자열을 GameType 으로 반환합니다
            return try {  //문자열이 enum ordinal 값일 경우
                val parseInt = Integer.parseInt(str)
                values()[parseInt]
            } catch (_: Exception) {
                try {  //문자열이 enum name 일 경우
                    valueOf(str.uppercase())
                } catch (_: Exception) {
                    values()[0]  //아니면, 첫번째 enum
                }
            }
        }
    }
}