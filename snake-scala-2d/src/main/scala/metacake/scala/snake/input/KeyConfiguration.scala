package metacake.scala.snake.input

import java.awt.event.KeyEvent

import io.metacake.core.input.ActionTrigger
import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.input.keyboard.KeyboardActionTrigger
import io.metacake.s2d.process.recognizers.keyboard.{KeyActionRecognizer, KeyClickActionRecognizer}

object KeyConfiguration {

  val W: ActionRecognizerName = new ActionRecognizerName("W")
  val wRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(W)
  val wTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_W).bindRecognizer(wRecognizer)

  val S: ActionRecognizerName = new ActionRecognizerName("S")
  val sRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(S)
  val sTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_S).bindRecognizer(sRecognizer)

  val A: ActionRecognizerName = new ActionRecognizerName("A")
  val aRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(A)
  val aTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_A).bindRecognizer(aRecognizer)

  val D: ActionRecognizerName = new ActionRecognizerName("D")
  val dRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(D)
  val dTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_D).bindRecognizer(dRecognizer)

  val ESCAPE: ActionRecognizerName = new ActionRecognizerName("ESC")
  val escRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(ESCAPE)
  val escTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_ESCAPE).bindRecognizer(escRecognizer)
}