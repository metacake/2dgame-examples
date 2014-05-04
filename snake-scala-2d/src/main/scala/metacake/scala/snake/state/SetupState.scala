package metacake.scala.snake.state

import io.metacake.core.process.state.{TransitionState, GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.ActionRecognizerPipe
import metacake.scala.snake.input.{KeyConfiguration => config}
import io.metacake.s2d.input.keyboard.KeyboardDevice

class SetupState extends UserState {

  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = {
    TransitionState.withState(new SnakeState())
                    .withTriggers(config.wTrigger, config.sTrigger, config.aTrigger, config.dTrigger)
                    .withBucket(KeyboardDevice.KEY, config.wRecognizer, config.sRecognizer, config.aRecognizer, config.dRecognizer)
                    .transition()
  }

  override def renderingInstructions(): RenderingInstructionBundle = RenderingInstructionBundle.EMPTY_BUNDLE
}