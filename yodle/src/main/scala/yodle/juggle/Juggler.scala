package yodle.juggle

// Joshua Calloway
// joshua.calloway@gmail.com
// July 27, 2012
// Yodle JugglerFest Interview Puzzle
import scala.collection.immutable.Map;

case class Juggler(val name:String, val hand:Int, val endurance:Int, val pizzazz:Int, val preferences:List[String])
{

  def *(that:Circuit): Int = hand * that.hand + endurance * that.endurance + pizzazz * that.pizzazz

  def preferencesWithFit(circuitsIndexed:Map[String,Circuit]) = {
    preferences.map( p => (circuitsIndexed(p), circuitsIndexed(p) * this))
  }
}

object Juggler {
  def apply(name:String, hand:String, endurance:String, pizzazz:String, preferences:String) = new Juggler(name, hand.toInt, endurance.toInt, pizzazz.toInt, List.fromArray(preferences.split(",")))
}
