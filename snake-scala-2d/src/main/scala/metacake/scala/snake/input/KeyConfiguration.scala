package metacake.scala.snake.input

import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.process.recognizers.keyboard.{KeyClickActionRecognizer, KeyReleaseActionRecognizer, KeyHoldActionRecognizer, KeyActionRecognizer}
import io.metacake.core.input.ActionTrigger
import java.awt.event.KeyEvent
import io.metacake.s2d.input.keyboard.KeyboardActionTrigger

object KeyConfiguration {

  val W: ActionRecognizerName = new ActionRecognizerName()
  val wRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(W)
  val wTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_W).bindRecognizer(wRecognizer)

  val S: ActionRecognizerName = new ActionRecognizerName()
  val sRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(S)
  val sTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_S).bindRecognizer(sRecognizer)

  val A: ActionRecognizerName = new ActionRecognizerName()
  val aRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(A)
  val aTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_A).bindRecognizer(aRecognizer)

  val D: ActionRecognizerName = new ActionRecognizerName()
  val dRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(D)
  val dTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_D).bindRecognizer(dRecognizer)

  val ESCAPE: ActionRecognizerName = new ActionRecognizerName()
  val escRecognizer: KeyActionRecognizer = new KeyClickActionRecognizer(ESCAPE)
  val escTrigger: ActionTrigger[KeyEvent] = new KeyboardActionTrigger(KeyEvent.VK_ESCAPE).bindRecognizer(escRecognizer)
}