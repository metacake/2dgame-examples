package metacake.scala.snake.state

import io.metacake.core.process.state.{TransitionState, GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.ActionRecognizerPipe
import metacake.scala.snake.input.{KeyConfiguration => conf}
import io.metacake.s2d.input.keyboard.KeyboardDevice
import metacake.scala.snake.entity.{Food, Direction, Snake, Segment}

class SetupState(segments: List[Segment]) extends UserState {

  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = {
    TransitionState.withState(new SnakeState(new Snake(Direction.RIGHT, segments), new Food(4, 4)))
                    .withTriggers(conf.wTrigger, conf.sTrigger, conf.aTrigger, conf.dTrigger, conf.escTrigger)
                    .withBucket(KeyboardDevice.KEY, conf.wRecognizer, conf.sRecognizer, conf.aRecognizer, conf.dRecognizer, conf.escRecognizer)
                    .transition()
  }

  override def renderingInstructions(): RenderingInstructionBundle = RenderingInstructionBundle.EMPTY_BUNDLE
}