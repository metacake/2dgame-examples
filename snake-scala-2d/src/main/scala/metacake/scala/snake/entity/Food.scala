package metacake.scala.snake.entity

import java.awt.{Color, Graphics2D}

import io.metacake.core.output.RenderingInstruction
import io.metacake.s2d.output.drawing.instructions.{DrawInstruction, PlaceInstruction, RectangleInstruction}

object Food {
  val COLOR: Color = Color.RED
  val SIZE: Int = 50
  val IMAGE: DrawInstruction = new RectangleInstruction(Food.SIZE, Food.SIZE, Food.COLOR)
}

class Food(val x: Int, val y: Int) extends Positionable with Drawable {
  override def draw(scene: DrawInstruction): RenderingInstruction[Graphics2D] = {
    new PlaceInstruction(Food.IMAGE, x * Food.SIZE, y * Food.SIZE, scene)
  }
}