package metacake.scala.snake.state

import io.metacake.core.process.state.{GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.ActionRecognizerPipe

class SetupState extends UserState {

  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = {

  }

  override def renderingInstructions(): RenderingInstructionBundle = RenderingInstructionBundle.EMPTY_BUNDLE
}