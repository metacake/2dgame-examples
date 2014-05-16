package metacake.scala.snake.state

import io.metacake.core.process.state.{EndState, GameState, UserState}
import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.ActionRecognizerPipe
import io.metacake.s2d.output.drawing.DrawingDevice
import io.metacake.s2d.output.drawing.instructions.{RectangleInstruction, DrawInstruction, TextInstruction, PlaceInstruction}
import java.awt.{Color, Font}
import metacake.scala.snake.SnakeApp
import ScoreState._
import metacake.scala.snake.entity.Snake

object ScoreState {
  val FONT: Font = new Font("Arial", Font.PLAIN, 16)
  val GAME_OVER: DrawInstruction = new TextInstruction("Game Over!", FONT, Color.BLACK)
  val SCENE: DrawInstruction = new PlaceInstruction(GAME_OVER, 250, 250, new RectangleInstruction(SnakeApp.WIDTH, SnakeApp.HEIGHT, Color.WHITE))
}

class ScoreState(snake: Snake) extends UserState {
  val score: Int = snake.segments.length

  override def tick(delta: Long, pipe: ActionRecognizerPipe): GameState = EndState.endWith(this)

  override def renderingInstructions(): RenderingInstructionBundle = {
    val bundle: RenderingInstructionBundle = new RenderingInstructionBundle()
    bundle.add(DrawingDevice.NAME, new PlaceInstruction(new TextInstruction("Your score is " + score, FONT, Color.BLACK), 250, 300, SCENE))
  }
}