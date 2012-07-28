package yodle.juggle

// Joshua Calloway
// joshua.calloway@gmail.com
// July 27, 2012
// Yodle JugglerFest Interview Puzzle

case class Circuit(val name:String, val hand:Int, val endurance:Int, val pizzazz:Int)
{

  // . product
  def *(that:Juggler): Int = hand * that.hand + endurance * that.endurance + pizzazz * that.pizzazz
}

object Circuit {
  def apply(name:String, hand:String, endurance:String, pizzazz:String) = new Circuit(name, hand.toInt, endurance.toInt, pizzazz.toInt)
}
