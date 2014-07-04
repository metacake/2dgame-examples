package metacake.scala.snake.state

import io.metacake.core.process.state.GameState
import io.metacake.core.process.state.GameState.Kind
import io.metacake.core.process.{ActionRecognizerPipe, Transition}
import io.metacake.s2d.input.keyboard.KeyboardDevice
import metacake.scala.snake.entity.{Direction, Food, Segment, Snake}
import metacake.scala.snake.input.{KeyConfiguration => conf}

class SetupState(segments: List[Segment]) extends GameState {

  override def kind(): Kind = Kind.NORMAL

  override def tick(delta: Long, pipe: ActionRecognizerPipe): Transition = {
    Transition.to(new SnakeState(new Snake(Direction.RIGHT, segments), new Food(4, 4)))
                    .withTriggers(conf.wTrigger, conf.sTrigger, conf.aTrigger, conf.dTrigger, conf.escTrigger)
                    .withBucket(KeyboardDevice.KEY, conf.wRecognizer, conf.sRecognizer, conf.aRecognizer, conf.dRecognizer, conf.escRecognizer)
  }
}