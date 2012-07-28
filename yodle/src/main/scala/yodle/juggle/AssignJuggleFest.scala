package yodle.juggle

// Joshua Calloway
// joshua.calloway@gmail.com
// July 27, 2012
// Yodle Triangle Interview Puzzle

import scala.io.Source
import scala.collection.mutable.Map;

case class AssignJuggleFest(val circuits:List[Circuit],jugglers:List[Juggler])
{
  def chooseMaxDotProduct(circuit:Circuit, jugglers:List[Juggler]) = {
    jugglers.reduceLeft( (j1, j2) => if (j1 * circuit > j2 * circuit) j1 else j2)
  }
  def chooseTeam(circuit:Circuit, jugglers:List[Juggler], teamSize:Int) = {
    var team = List[Juggler]()

    for (i <- 0 until teamSize) {
      val juggler = chooseMaxDotProduct(circuit,jugglers.diff(team))
      team = team ::: List(juggler)
    }
    team
  }

  def rememberJugglerAssignments(circuit: Circuit, jugglers:List[Juggler], assignments:Map[Juggler, List[Circuit]]) = {
    jugglers.foreach( j => if (assignments.contains(j)) assignments(j) :::= List(circuit) else assignments(j) = List(circuit) )
    assignments
  }

  def assignJuggleFest = {
    println("jugglers : " + jugglers.length)
    println("circuits : " + circuits.length)
    val circuitsIndexed = circuits groupBy(_.name) mapValues( C => C(0) )
    println("circuitsIndex: " + circuitsIndexed)
    val teamSize = jugglers.length / circuits.length
    var remainingJugglers = jugglers
    var jugglerAssignments = Map[Juggler, Circuit]()
    var circuitAssignments = Map[Circuit, List[Juggler]]()

    val assignments = jugglers.map(J => {
      val pref = J.preferencesWithFit(circuitsIndexed)
      

    })
    assignments
  }

  def assignJuggleFest2 = {
    println("jugglers : " + jugglers.length)
    println("circuits : " + circuits.length)
    val teamSize = jugglers.length / circuits.length
    var remainingJugglers = jugglers
    var jugglerAssignments = Map[Juggler, List[Circuit]]()
    val assignments = circuits.map(C => {
      val chosen = chooseTeam(C, jugglers, teamSize)
      val c = CircuitAssignment(C, chosen)
      remainingJugglers = remainingJugglers.diff(chosen)
      jugglerAssignments = rememberJugglerAssignments(c.circuit, chosen, jugglerAssignments)
      c
    })
    println("remaining: " + jugglerAssignments)
    assignments
  }
}


object AssignJuggleFest
{

  def main(args: Array[String]) {
    val parser = ParseJuggleFestFile(args(0))
    val(circuits,jugglers) = parser.parseFile
    val c = AssignJuggleFest(circuits,jugglers)
    c.assignJuggleFest
  }

}
