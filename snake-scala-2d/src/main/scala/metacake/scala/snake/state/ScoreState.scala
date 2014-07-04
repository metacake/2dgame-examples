package metacake.scala.snake.state

import java.awt.{Color, Font}

import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.state.GameState.Kind
import io.metacake.core.process.state.{EndState, GameState}
import io.metacake.core.process.{ActionRecognizerPipe, Transition}
import io.metacake.s2d.output.drawing.DrawingDevice
import io.metacake.s2d.output.drawing.instructions.{DrawInstruction, PlaceInstruction, RectangleInstruction, TextInstruction}
import metacake.scala.snake.SnakeApp
import metacake.scala.snake.entity.Snake
import metacake.scala.snake.state.ScoreState._

object ScoreState {
  val FONT: Font = new Font("Arial", Font.PLAIN, 16)
  val GAME_OVER: DrawInstruction = new TextInstruction("Game Over!", FONT, Color.BLACK)
  val SCENE: DrawInstruction = new PlaceInstruction(GAME_OVER, 250, 250, new RectangleInstruction(SnakeApp.WIDTH, SnakeApp.HEIGHT, Color.WHITE))
}

class ScoreState(snake: Snake) extends RenderableState {
  val score: Int = snake.segments.length

  override def tick(delta: Long, pipe: ActionRecognizerPipe): Transition = Transition.to(EndState.end())

  def render(): RenderingInstructionBundle = {
    val bundle: RenderingInstructionBundle = new RenderingInstructionBundle()
    bundle.add(DrawingDevice.NAME, new PlaceInstruction(new TextInstruction("Your score is " + score, FONT, Color.BLACK), 250, 300, SCENE))
  }

  override def kind(): Kind = Kind.NORMAL
}