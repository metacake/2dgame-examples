package metacake.scala.snake.state

import io.metacake.core.process.state.{GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.{ActionRecognizerName, ActionRecognizerPipe}
import metacake.scala.snake.entity.{SnakeSegment, SnakeEntity}
import io.metacake.s2d.output.drawing.DrawingDevice
import java.awt.Color
import metacake.scala.snake.Snake
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction
import io.metacake.s2d.input.keyboard.KeyboardDevice
import io.metacake.core.common.CustomizableMap
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer
import metacake.scala.snake.input.KeyConfiguration

class SnakeState(snake: SnakeEntity) extends UserState {
  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = {
    new SnakeState(handleKeyboardAction(pipe.emptyBucket(KeyboardDevice.KEY)))
  }

  private def handleKeyboardAction(recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer]): SnakeEntity = {
    if (recognizers.get(KeyConfiguration.W).wasTriggered()) moveSnakeSegments(0, -1)
    else if (recognizers.get(KeyConfiguration.S).wasTriggered()) moveSnakeSegments(0, 1)
    else if (recognizers.get(KeyConfiguration.A).wasTriggered()) moveSnakeSegments(-1, 0)
    else if (recognizers.get(KeyConfiguration.D).wasTriggered()) moveSnakeSegments(1, 0)
    else snake
  }

  private def moveSnakeSegments(x: Int, y: Int): SnakeEntity = {
    new SnakeEntity(headInDirection(x, y) :: snake.segments.dropRight(1))
  }

  private def headInDirection(x: Int, y: Int): SnakeSegment = {
    def head: SnakeSegment = snake.segments.head
    new SnakeSegment(head.x + x, head.y + y)
  }

  override def renderingInstructions(): RenderingInstructionBundle = {
    def bundle = new RenderingInstructionBundle()
    bundle.add(DrawingDevice.NAME, snake.draw(new RectangleInstruction(Snake.WIDTH, Snake.HEIGHT, Color.WHITE)))
  }
}