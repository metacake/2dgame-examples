package metacake.scala.snake.entity

import metacake.scala.snake.UnitSpec
import metacake.scala.snake.entity.Direction._

class SnakeEntitySpec extends UnitSpec {

  "A SnakeEntity" should "move in the correct direction" in {
    assert(new SnakeEntity(UP, List(new SnakeSegment(0, 0))).move() == new SnakeEntity(UP, List(new SnakeSegment(0, -1))))
    assert(new SnakeEntity(DOWN, List(new SnakeSegment(0, 0))).move() == new SnakeEntity(DOWN, List(new SnakeSegment(0, 1))))
    assert(new SnakeEntity(LEFT, List(new SnakeSegment(0, 0))).move() == new SnakeEntity(LEFT, List(new SnakeSegment(-1, 0))))
    assert(new SnakeEntity(RIGHT, List(new SnakeSegment(0, 0))).move() == new SnakeEntity(RIGHT, List(new SnakeSegment(1, 0))))
  }
}