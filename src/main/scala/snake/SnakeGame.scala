package snake

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

case class SnakeGame(dimension: Dimension, var snake: Snake, var food: Food) {
  val (width,height ) = dimension
  var state = GameStates.ONGOING

  /**Makes next move in game*/
  private def makeMove(c:Char) = {
    if(snake.possibleDirections.contains(c))
      walkSnake(c)
    else println("Invalid Direction. Try one of w,a,s,d.")
  }

  /**Starts the game.*/
  final def play = {
    while(state==GameStates.ONGOING) {
      println("\u001b[2J")
      board(width,height)(snake,food).print
      Try(makeMove(recordInput)) match {
        case Failure(exception) =>
          println(Console.RED + exception.getMessage + Console.RESET)
          state = GameStates.LOST
        case Success(_) => ()
      }
    }
  }

  /**Takes input from user for making moves.*/
  @tailrec
  private def recordInput:Char = {
    println("Please enter next move(w,a,s,d): ")
    val line = scala.io.StdIn.readLine.headOption
    if(line.isEmpty) recordInput
    else line.get
  }

  /**Walks snake to next step*/
  private def walkSnake(c:Char) = {
    if(c!=snake.oppositeDirection(snake.direction)) {
      val head = snake.newHead(c)(width,height)
      snake = Snake((head :: snake.snake.toList):_*)
      if(head==food) food = food.process(width,height)(snake, this)
      else snake = Snake(snake.snake.dropRight(1):_*)
      snake.direction = c
    }
  }

}