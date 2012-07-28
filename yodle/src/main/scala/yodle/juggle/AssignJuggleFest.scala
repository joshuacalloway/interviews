package yodle.juggle

// Joshua Calloway
// joshua.calloway@gmail.com
// July 27, 2012
// Yodle Triangle Interview Puzzle

import scala.io.Source

case class AssignJuggleFest(val filename:String)
{
  val JuggerPattern = """J ([\w\.]+) H:([0-9]+) E:([0-9]+) P:([0-9]+) (.*)""".r
  val CircuitPattern = """C ([\w\.]+) H:([0-9]+) E:([0-9]+) P:([0-9]+)""".r

  def parseJuggleFest = {
    val src = Source.fromURL(getClass.getResource(filename))
    val iter = src.getLines()
    var jugglers = List[Juggler]()
    var circuits = List[Circuit]()
    while (iter.hasNext) {
      val line = iter.next
      line match {
	case JuggerPattern(name, hand, endurance, pizzazz, pref) => {
	  jugglers :::= List(Juggler(name, hand, endurance, pizzazz, pref))
	 
	}
	case CircuitPattern(name, hand, endurance, pizzazz) => {
	  circuits :::= List(Circuit(name, hand, endurance, pizzazz))
	  println("Circuit: " + line)
	  println("name : " + name)

	}
	case _ => {
	  println("Unknown line: " + line)
	}
      }
    }
    println("jugglers : " + jugglers.length)
    val j = jugglers(1)
    println("circuits : " + circuits.length)
  }

  def assignJuggleFest = {
    parseJuggleFest
  }
}


object AssignJuggleFest
{

  def main(args: Array[String]) {
    val c = AssignJuggleFest(args(0))
    c.assignJuggleFest
  }

}
