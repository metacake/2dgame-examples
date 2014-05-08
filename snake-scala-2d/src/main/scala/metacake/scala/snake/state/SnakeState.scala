package metacake.scala.snake.state

import io.metacake.core.process.state.{EndState, GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.{ActionRecognizerName, ActionRecognizerPipe}
import metacake.scala.snake.entity.SnakeEntity
import io.metacake.s2d.output.drawing.DrawingDevice
import java.awt.Color
import metacake.scala.snake.Snake
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction
import io.metacake.s2d.input.keyboard.KeyboardDevice
import io.metacake.core.common.CustomizableMap
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer
import metacake.scala.snake.entity.Direction._
import metacake.scala.snake.input.KeyConfiguration

class SnakeState(snake: SnakeEntity) extends UserState {
  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = {
    def recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer] = pipe.emptyBucket(KeyboardDevice.KEY)
    if (recognizers.get(KeyConfiguration.ESCAPE).triggerWeight() > 0) {
      EndState.closeWith(this)
    } else {
      new SnakeState(new SnakeEntity(getDirection(recognizers), snake.segments).move())
    }
  }

  def getDirection(recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer]): Direction = {
    if (recognizers.get(KeyConfiguration.W).triggerWeight() > 0) UP
    else if (recognizers.get(KeyConfiguration.S).triggerWeight() > 0) DOWN
    else if (recognizers.get(KeyConfiguration.A).triggerWeight() > 0) LEFT
    else if (recognizers.get(KeyConfiguration.D).triggerWeight() > 0) RIGHT
    else snake.dir
  }

  override def renderingInstructions(): RenderingInstructionBundle = {
    def bundle = new RenderingInstructionBundle()
    bundle.add(DrawingDevice.NAME, snake.draw(new RectangleInstruction(Snake.WIDTH, Snake.HEIGHT, Color.WHITE)))
  }
}