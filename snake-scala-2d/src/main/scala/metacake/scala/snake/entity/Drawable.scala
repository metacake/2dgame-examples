package metacake.scala.snake.entity

import java.awt.Graphics2D

import io.metacake.core.output.RenderingInstruction
import io.metacake.s2d.output.drawing.instructions._

trait Drawable {
  def draw(scene: DrawInstruction): RenderingInstruction[Graphics2D]
}