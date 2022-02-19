package me.brucefreedy.macro

import me.brucefreedy.adofai.AdofaiResult
import me.brucefreedy.adofai.TileUtil
import me.brucefreedy.game.GameType
import java.awt.FlowLayout
import java.awt.Frame
import java.awt.Label
import java.awt.Robot
import java.awt.event.KeyEvent
import java.awt.event.KeyListener


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val robot = Robot()
            val adofaiResult =
                GameType.A_DANCE_OF_FIRE_AND_ICE.parser.parse("C:\\Users\\Bruce\\Desktop\\my_adofai.adofai") as AdofaiResult
            val calcDelay = adofaiResult.calcDelay
            val iterator = calcDelay.iterator()
            val f = Frame("Macro")
            f.layout = FlowLayout()
            f.setSize(500, 500)
            val l = Label()
            l.text = "This is a macro"
            f.add(l)
            f.isVisible = false
            val keyListenerMacro = KeyListenerMacro()
            f.addKeyListener(keyListenerMacro)
            println("ready for 3s...")
            Thread.sleep(3000L)
            robot.keyPress(80)
            val startBpm = adofaiResult.startBpm
            var count = 20 + TileUtil.toMillisecond(
                (((startBpm%160)/160.0*360+180)%360+(adofaiResult.countDownTick+1)*180.0), startBpm
            ).apply { println("ready: $this") }
            val startTime = System.currentTimeMillis()
            while (iterator.hasNext()) {
                if (count <= System.currentTimeMillis() - startTime) {
                    count += iterator.next()
                    robot.keyPress(32)
                    robot.keyRelease(32)
                    println("Boom  ")
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

class KeyListenerMacro : KeyListener {
    var pressed = false
    override fun keyTyped(p0: KeyEvent?) = Unit

    override fun keyPressed(p0: KeyEvent?) {
        println("pressed")
        if (p0!!.keyCode == 80) pressed = true
    }

    override fun keyReleased(p0: KeyEvent?) = Unit

}