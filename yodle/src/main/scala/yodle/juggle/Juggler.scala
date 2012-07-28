package yodle.juggle

// Joshua Calloway
// joshua.calloway@gmail.com
// July 27, 2012
// Yodle JugglerFest Interview Puzzle

case class Juggler(val name:String, val hand:Int, val endurance:Int, val pizzazz:Int, val preferences:List[String])
{

}

object Juggler {
  def apply(name:String, hand:String, endurance:String, pizzazz:String, preferences:String) = new Juggler(name, hand.toInt, endurance.toInt, pizzazz.toInt, List.fromArray(preferences.split(",")))
}
