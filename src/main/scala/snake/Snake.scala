package snake

case class Snake(snake:Cell*) {

  var direction: Char = 'w'
  lazy val oppositeDirection: Map[Char,Char] = Map('w'->'s','a'->'d','s'->'w','d'->'a')
  val possibleDirections: List[Char] = List('w','a','s','d')

  /**Helps calculating new head to snake.*/
  def newHead(c:Char)(width: Int, height: Int): Cell = {
    val operation = c match {
      case 'd' => (0,1)
      case 's' => (1,0)
      case 'a' => (0,-1)
      case 'w' => (-1,0)
      case  _  => (0,0)
    }
    val h = (snake.head._1+operation._1,snake.head._2+operation._2) match {
      case (x,y) if snake.contains((x,y)) => throw new Exception("Game over.")
      case (x,y) if x>=width  => (0,y)
      case (x,y) if y>=height => (x,0)
      case (x,y) if x<0 => (width-1,y)
      case (x,y) if y<0 => (x,height-1)
      case a            => a
    }
    h
  }


}
