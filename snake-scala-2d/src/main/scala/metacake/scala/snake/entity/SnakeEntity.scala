package metacake.scala.snake.entity

import java.awt.Color
import io.metacake.s2d.output.drawing.instructions._
import metacake.scala.snake.entity.Direction.Direction

object SnakeEntity {
  val SNAKE_COLOR: Color = Color.GREEN
  val SNAKE_SIZE: Int = 50
}

object Direction extends Enumeration {
  type Direction = Value
  val UP, DOWN, LEFT, RIGHT = Value
}

class SnakeSegment(val x: Int, val y: Int) extends Positionable {
  def image(): FilledShapeInstruction = new RectangleInstruction(SnakeEntity.SNAKE_SIZE, SnakeEntity.SNAKE_SIZE, SnakeEntity.SNAKE_COLOR)

  override def equals(o: Any): Boolean = o match {
    case that: SnakeSegment => this.x == that.x && this.y == that.y
    case _ => false
  }
  override def toString: String = "(" + x +", " + y + ")"
}

class SnakeEntity(val dir: Direction, val segments: List[SnakeSegment]) extends Drawable {

  def move(): SnakeEntity = {
    def head: SnakeSegment = dir match {
      case Direction.UP => headInDirection(0, -1)
      case Direction.DOWN => headInDirection(0, 1)
      case Direction.LEFT => headInDirection(-1, 0)
      case Direction.RIGHT => headInDirection(1, 0)
    }
    new SnakeEntity(dir, head :: segments.dropRight(1))
  }

  private def headInDirection(x: Int, y: Int): SnakeSegment = {
    def head: SnakeSegment = segments.head
    new SnakeSegment(head.x + x, head.y + y)
  }

  override def draw(scene: DrawInstruction): DrawInstruction = {
    def offset(pos: Int): Int = pos * SnakeEntity.SNAKE_SIZE
    segments.foldLeft(scene)({(acc: DrawInstruction, segment: SnakeSegment) =>
      new PlaceInstruction(segment.image(), offset(segment.x), offset(segment.y), acc)
    })
  }

  override def equals(o: Any): Boolean = o match {
    case that: SnakeEntity => this.segments == that.segments
    case _ => false
  }
  override def toString: String = segments.toString()
}