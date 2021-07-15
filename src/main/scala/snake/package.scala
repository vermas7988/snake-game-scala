import scala.annotation.tailrec
import scala.util.Random

package object snake {

  type Board = Seq[Seq[String]]
  type Cell  = (Int,Int)
  type Dimension = (Int,Int)
  type Food  = (Int,Int)

  object GameStates {
    val ONGOING = "ONGOING"
    val WON     = "WON"
    val LOST    = "LOST"
  }

  /** Returns the current state of board */
  def board(width: Int, height: Int)(snake: Snake,food: Food): Board =
    (0 until width).map(a=>{
      (0 until height).map(b=>getCellString(a,b)(snake,food))
    })

  /**Helps calculating the current element in board*/
  private def getCellString(x:Int,y:Int)(snake: Snake,food: Food):String = {
    if(food == (x,y)) Console.YELLOW + "+" + Console.RESET
    else if(snake.snake.contains((x,y))) Console.GREEN +
      (if(snake.snake.head==(x,y)) "*" else "#") +
      Console.RESET
    else "."
  }

  implicit class BoardHelper(board:Board) {
    /**Prints the board state*/
    def print =
      board.foreach(a=> println(a.mkString("")))
  }

  implicit class FoodClass(food:Food) {
    /**Spawns food to new location once food is ate.*/
    @tailrec
    final def process(width: Int, height: Int)(snake: Snake):(Int,Int) = {
      (Random.nextInt(width),Random.nextInt(height)) match {
        case x if snake.snake.contains(x) => process(width: Int,height: Int)(snake: Snake)
        case x                        => x
      }
    }
  }

  

}
