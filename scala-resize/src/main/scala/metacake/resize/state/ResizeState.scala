package metacake.resize.state

import io.metacake.core.process.state.GameState
import io.metacake.core.process.state.GameState.Kind
import io.metacake.core.process.{ActionRecognizerPipe, Transition}
import io.metacake.s2d.input.window.WindowDevice

class ResizeState extends GameState {
  override def tick(delta: Long, pipe: ActionRecognizerPipe): Transition = {
    if (WindowDevice.Recognizer.wasTriggered()) {
      println(WindowDevice.Recognizer.getSize)
    }
    Transition.to(this)
  }

  override def kind(): Kind = Kind.NORMAL
}