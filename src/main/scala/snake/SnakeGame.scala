package snake


import scala.annotation.tailrec
import scala.util.{Failure, Random, Success, Try}

case class SnakeGame(width: Int, height: Int) {
  type Snake = List[(Int,Int)]
  type Food  = (Int,Int)

  val gameStates = GameStates

  var snake: Snake = List((3,5),(4,5),(5,5))
  var snakeDirection: Char = 'w'
  var food: Food  = (1,3)
  var state = gameStates.ONGOING

  lazy val oppositeDirection: Map[Char,Char] = Map('w'->'s','a'->'d','s'->'w','d'->'a')
  val possibleDirections: List[Char] = List('w','a','s','d')

  /** Returns the current state of board */
  private def board: Seq[Seq[String]] = (0 until width).map(a=>{
    (0 until height).map(b=>getBoardElem(a,b))
  })

  /**Helps calculating the current element in board*/
  private def getBoardElem(x:Int,y:Int):String = {
    if(food == (x,y)) Console.YELLOW + "+" + Console.RESET
    else if(snake.contains((x,y))) Console.GREEN +
      (if(snake.head==(x,y)) "*" else "#") +
      Console.RESET
    else "."
  }

  /**Makes next move in game*/
  private def makeMove(c:Char) = {
    if(possibleDirections.contains(c))
      walkSnake(c)
    else println("Invalid Direction. Try one of w,a,s,d.")
  }

  /**Spawns food to new location once food is ate.*/
  @tailrec
  private def newFood:(Int,Int) = {
    (Random.nextInt(width),Random.nextInt(height)) match {
      case x if snake.contains(x) => newFood
      case x                      => x
    }
  }

  /**Helps calculating new head to snake.*/
  private def newHead(c:Char) = {
    val operation = c match {
      case 'd' => (0,1)
      case 's' => (1,0)
      case 'a' => (0,-1)
      case 'w' => (-1,0)
      case  _  => (0,0)
    }
    val head = (snake.head._1+operation._1,snake.head._2+operation._2) match {
      case (x,y) if snake.contains((x,y)) => throw new Exception("Game over.")
      case (x,y) if x>=width  => (0,y)
      case (x,y) if y>=height => (x,0)
      case (x,y) if x<0 => (width-1,y)
      case (x,y) if y<0 => (x,height-1)
      case a            => a
    }
    head
  }

  /**Starts the game.*/
  final def play = {
    while(state==gameStates.ONGOING) {
      println("\u001b[2J")
      printBoardState
      if(snake.size==width*height) {
        state = gameStates.WON
        println(Console.GREEN + "Game Completed. You won." + Console.RESET)
      } else
        Try(makeMove(recordInput)) match {
          case Failure(exception) =>
            println(Console.RED + exception.getMessage + Console.RESET)
            state = gameStates.LOST
          case Success(_) => ()
        }
    }
  }

  /**Prints the board state*/
  private def printBoardState = {
    board.foreach(a=> println(a.mkString("")))
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
    if(c!=oppositeDirection(snakeDirection)) {
      val head = newHead(c)
      snake = head :: snake
      if(head==food) food = newFood
      else snake = snake.dropRight(1)
      snakeDirection = c
    }
  }

}