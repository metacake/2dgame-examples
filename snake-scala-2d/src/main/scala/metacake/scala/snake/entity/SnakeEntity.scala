package metacake.scala.snake.entity

import java.awt.Color
import io.metacake.s2d.output.drawing.instructions._
import scala.collection.immutable.StringOps

object SnakeEntity {
  val SNAKE_COLOR: Color = Color.GREEN
  val SNAKE_SIZE: Int = 50
}

class SnakeSegment(val x: Int, val y: Int) extends Positionable {
  def image(): FilledShapeInstruction = new RectangleInstruction(SnakeEntity.SNAKE_SIZE, SnakeEntity.SNAKE_SIZE, SnakeEntity.SNAKE_COLOR)

  override def toString: String = "(" + x +", " + y + ")"
}

class SnakeEntity(val segments: List[SnakeSegment]) extends Drawable {
  override def draw(scene: DrawInstruction): DrawInstruction = {
    def offset(pos: Int): Int = pos * SnakeEntity.SNAKE_SIZE
    segments.foldLeft(scene)({(acc: DrawInstruction, segment: SnakeSegment) =>
      new PlaceInstruction(segment.image(), offset(segment.x), offset(segment.y), acc)
    })
  }

  override def toString: String = segments.toString()
}