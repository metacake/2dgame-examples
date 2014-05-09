package metacake.scala.snake.state

import io.metacake.core.process.state.{EndState, GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.{ActionRecognizerName, ActionRecognizerPipe}
import metacake.scala.snake.entity.{Food, Snake}
import io.metacake.s2d.output.drawing.DrawingDevice
import java.awt.Color
import metacake.scala.snake.SnakeApp
import io.metacake.s2d.output.drawing.instructions.{DrawInstruction, RectangleInstruction}
import io.metacake.s2d.input.keyboard.KeyboardDevice
import io.metacake.core.common.CustomizableMap
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer
import metacake.scala.snake.entity.Direction._
import metacake.scala.snake.input.{KeyConfiguration => conf}

object SnakeState {
  val SCENE: DrawInstruction = new RectangleInstruction(SnakeApp.WIDTH, SnakeApp.HEIGHT, Color.WHITE)
}

class SnakeState(snake: Snake, food: Food) extends UserState {
  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = {
    def recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer] = pipe.emptyBucket(KeyboardDevice.KEY)
    if (recognized(recognizers, conf.ESCAPE) || outsideBounds(snake)) EndState.closeWith(this)
    else new SnakeState(new Snake(getDirection(recognizers), snake.segments).move(), this.food)
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

  private def outsideBounds(snake: Snake): Boolean = {
    def head = snake.segments.head
    head.x < 0 || head.x >= (SnakeApp.WIDTH / Snake.SIZE) || head.y < 0 || head.y >= (SnakeApp.HEIGHT / Snake.SIZE)
  }

  override def renderingInstructions(): RenderingInstructionBundle = {
    def bundle = new RenderingInstructionBundle()
    bundle.add(DrawingDevice.NAME, food.draw(snake.draw(SnakeState.SCENE)))
  }
}