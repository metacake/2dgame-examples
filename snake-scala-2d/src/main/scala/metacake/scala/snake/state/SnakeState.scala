package metacake.scala.snake.state

import java.awt.Color

import io.metacake.core.common.CustomizableMap
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.state.GameState.Kind
import io.metacake.core.process.{ActionRecognizerName, ActionRecognizerPipe, Transition}
import io.metacake.s2d.input.keyboard.KeyboardDevice
import io.metacake.s2d.output.drawing.DrawingDevice
import io.metacake.s2d.output.drawing.instructions.{DrawInstruction, RectangleInstruction}
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer
import metacake.scala.snake.SnakeApp
import metacake.scala.snake.entity.Direction._
import metacake.scala.snake.entity.{Food, Snake}
import metacake.scala.snake.input.{KeyConfiguration => conf}

import scala.util.Random

object SnakeState {
  val SCENE: DrawInstruction = new RectangleInstruction(SnakeApp.WIDTH, SnakeApp.HEIGHT, Color.WHITE)
  val BOARD_WIDTH: Int = SnakeApp.WIDTH / Snake.SIZE
  val BOARD_HEIGHT: Int = SnakeApp.HEIGHT / Snake.SIZE
}

class SnakeState(snake: Snake, food: Food) extends RenderableState {

  override def tick(delta: Long, pipe: ActionRecognizerPipe): Transition = {
    def recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer] = pipe.emptyBucket(KeyboardDevice.KEY)
    val state: RenderableState = if (recognized(recognizers, conf.ESCAPE) || outsideBounds(snake) || snake.collidesWithSelf()) {
      new ScoreState(snake)
    } else if (isEating) createMoreFood(recognizers)
      else new SnakeState(new Snake(getDirection(recognizers), snake.segments).move(), this.food)
    Transition.to(state).withInstructions(state.render())
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
    head.x < 0 || head.x >= SnakeState.BOARD_WIDTH || head.y < 0 || head.y >= SnakeState.BOARD_HEIGHT
  }

  private def isEating: Boolean = snake.segments.head.x == food.x && snake.segments.head.y == food.y

  private def createMoreFood(recognizers: CustomizableMap[ActionRecognizerName, KeyActionRecognizer]): SnakeState = {
    def grown: Snake = new Snake(getDirection(recognizers), snake.segments).grow()
    def rand: Random = new Random()
    new SnakeState(grown, new Food(rand.nextInt(SnakeState.BOARD_WIDTH), rand.nextInt(SnakeState.BOARD_WIDTH)))
  }

  def render(): RenderingInstructionBundle = {
    new RenderingInstructionBundle().add(DrawingDevice.NAME, food.draw(snake.draw(SnakeState.SCENE)))
  }

  override def kind(): Kind = Kind.NORMAL
}