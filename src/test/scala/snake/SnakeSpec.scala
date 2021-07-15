package snake

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SnakeSpec extends AnyFlatSpec with Matchers {

  "The Snake head " should "move upwards when its not in row 0 and w is pressed" in {
    val snake: Snake = Snake((3,5),(4,5),(5,5))
    val newHead = snake.newHead('w')(7,7)
    assert(newHead==(2,5))
  }
  "The Snake head " should " spawn in last row when its in row 0 and w is pressed" in {
    val snake: Snake = Snake((0,5),(1,5),(2,5))
    val newHead = snake.newHead('w')(7,7)
    assert(newHead==(6,5))
  }

  "The Snake head " should "move left when its not in column 0 and a is pressed" in {
    val snake: Snake = Snake((3,3),(3,4),(3,5))
    val newHead = snake.newHead('a')(7,7)
    assert(newHead==(3,2))
  }
  "The Snake head " should " spawn in last column when its in first column and a is pressed" in {
    val snake: Snake = Snake((5,0),(5,1),(5,2))
    val newHead = snake.newHead('a')(7,7)
    assert(newHead==(5,6))
  }

  "The Snake head " should "move right when its not in column 6 and d is pressed" in {
    val snake: Snake = Snake((3,5),(3,4),(3,3))
    val newHead = snake.newHead('d')(7,7)
    assert(newHead==(3,6))
  }
  "The Snake head " should " spawn in first column when its in last column and d is pressed" in {
    val snake: Snake = Snake((5,6),(5,6),(5,4))
    val newHead = snake.newHead('d')(7,7)
    assert(newHead==(5,0))
  }

  "The Snake head " should "move downwards when its not in row 6 and s is pressed" in {
    val snake: Snake = Snake((5,5),(4,5),(3,5))
    val newHead = snake.newHead('s')(7,7)
    assert(newHead==(6,5))
  }
  "The Snake head " should " spawn in first row when its in row 6 and s is pressed" in {
    val snake: Snake = Snake((6,5),(5,5),(4,5))
    val newHead = snake.newHead('s')(7,7)
    assert(newHead==(0,5))
  }

}
