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
import metacake.scala.snake.input.{KeyConfiguration => conf}

class SnakeState(snake: SnakeEntity) extends UserState {
  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = {
    def recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer] = pipe.emptyBucket(KeyboardDevice.KEY)
    if (recognized(recognizers, conf.ESCAPE) || outsideBounds(snake)) EndState.closeWith(this)
    else new SnakeState(new SnakeEntity(getDirection(recognizers), snake.segments).move())
  }

  private def getDirection(recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer]): Direction = {
    if (recognized(recognizers, conf.W)) UP
    else if (recognized(recognizers, conf.S)) DOWN
    else if (recognized(recognizers, conf.A)) LEFT
    else if (recognized(recognizers, conf.D)) RIGHT
    else snake.dir
  }

  private def recognized(recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer], name: ActionRecognizerName): Boolean = {
    recognizers.get(name).triggerWeight() > 0
  }

  private def outsideBounds(snake: SnakeEntity): Boolean = {
    def head = snake.segments.head
    head.x < 0 || head.x >= (Snake.WIDTH / 50) || head.y < 0 || head.y >= (Snake.HEIGHT / 50)
  }

  override def renderingInstructions(): RenderingInstructionBundle = {
    def bundle = new RenderingInstructionBundle()
    bundle.add(DrawingDevice.NAME, snake.draw(new RectangleInstruction(Snake.WIDTH, Snake.HEIGHT, Color.WHITE)))
  }
}