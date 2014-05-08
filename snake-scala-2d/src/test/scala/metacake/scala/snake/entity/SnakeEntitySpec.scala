package metacake.scala.snake.entity

import metacake.scala.snake.UnitSpec
import metacake.scala.snake.entity.Direction._

class SnakeEntitySpec extends UnitSpec {

  "A SnakeEntity" should "move in the correct direction" in {
    assert(new Snake(UP, List(new Segment(0, 0))).move() == new Snake(UP, List(new Segment(0, -1))))
    assert(new Snake(DOWN, List(new Segment(0, 0))).move() == new Snake(DOWN, List(new Segment(0, 1))))
    assert(new Snake(LEFT, List(new Segment(0, 0))).move() == new Snake(LEFT, List(new Segment(-1, 0))))
    assert(new Snake(RIGHT, List(new Segment(0, 0))).move() == new Snake(RIGHT, List(new Segment(1, 0))))
  }
}