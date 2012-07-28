package yodle.juggle

// Joshua Calloway
// joshua.calloway@gmail.com
// July 27, 2012
// Yodle Triangle Interview Puzzle

import scala.io.Source

case class ParseJuggleFestFile(val filename:String)
{
  val JuggerPattern = """J ([\w\.]+) H:([0-9]+) E:([0-9]+) P:([0-9]+) (.*)""".r
  val CircuitPattern = """C ([\w\.]+) H:([0-9]+) E:([0-9]+) P:([0-9]+)""".r

  def parseFile = {
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
	}
	case _ => {
	  println("Unknown line: " + line)
	}
      }
    }
    (circuits, jugglers)
  }
}
