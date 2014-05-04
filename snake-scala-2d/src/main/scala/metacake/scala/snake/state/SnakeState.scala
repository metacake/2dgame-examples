package metacake.scala.snake.state

import io.metacake.core.process.state.{GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.ActionRecognizerPipe

class SnakeState extends UserState {
  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = this

  override def renderingInstructions(): RenderingInstructionBundle = RenderingInstructionBundle.EMPTY_BUNDLE
}