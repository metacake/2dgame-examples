package metacake.scala.snake.state

import io.metacake.core.output.RenderingInstructionBundle
import io.metacake.core.process.state.GameState

trait RenderableState extends GameState {
  def render(): RenderingInstructionBundle
}