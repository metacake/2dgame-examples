package metacake.scala.snake.state

import io.metacake.core.process.state.{GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.ActionRecognizerPipe
import metacake.scala.snake.entity.SnakeEntity
import io.metacake.s2d.output.drawing.DrawingDevice
import java.awt.Color
import metacake.scala.snake.Snake
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction

class SnakeState(snake: SnakeEntity) extends UserState {
  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = this

  override def renderingInstructions(): RenderingInstructionBundle = {
    def bundle = new RenderingInstructionBundle()
    bundle.add(DrawingDevice.NAME, snake.draw(new RectangleInstruction(Snake.WIDTH, Snake.HEIGHT, Color.WHITE)))
  }
}