package metacake.scala.snake.entity

import java.awt.Color
import io.metacake.s2d.output.drawing.instructions._
import metacake.scala.snake.entity.Direction.Direction

object Snake {
  val COLOR: Color = Color.GREEN
  val SIZE: Int = 50
  val IMAGE: DrawInstruction = new RectangleInstruction(Snake.SIZE, Snake.SIZE, Snake.COLOR)
}

object Direction extends Enumeration {
  type Direction = Value
  val UP, DOWN, LEFT, RIGHT = Value
}

class Segment(val x: Int, val y: Int) extends Positionable {
  override def equals(o: Any): Boolean = o match {
    case that: Segment => this.x == that.x && this.y == that.y
    case _ => false
  }
  override def toString: String = "(" + x +", " + y + ")"
}

class Snake(val dir: Direction, val segments: List[Segment]) extends Drawable {

  def move(): Snake = new Snake(dir, nextHead() :: segments.dropRight(1))

  def grow(): Snake = {
    def tail: Segment = segments.last
    new Snake(dir, (nextHead() :: segments.dropRight(1)) :+ new Segment(tail.x, tail.y))
  }

  private def nextHead(): Segment = dir match {
    case Direction.UP => headInDirection(0, -1)
    case Direction.DOWN => headInDirection(0, 1)
    case Direction.LEFT => headInDirection(-1, 0)
    case Direction.RIGHT => headInDirection(1, 0)
  }

  private def headInDirection(x: Int, y: Int): Segment = {
    def head = segments.head
    new Segment(x + head.x, y + head.y)
  }

  override def draw(scene: DrawInstruction): DrawInstruction = {
    def offset(pos: Int): Int = pos * Snake.SIZE
    segments.foldLeft(scene)({(acc: DrawInstruction, segment: Segment) =>
      new PlaceInstruction(Snake.IMAGE, offset(segment.x), offset(segment.y), acc)
    })
  }

  override def equals(o: Any): Boolean = o match {
    case that: Snake => this.segments == that.segments
    case _ => false
  }
  override def toString: String = segments.toString()
}