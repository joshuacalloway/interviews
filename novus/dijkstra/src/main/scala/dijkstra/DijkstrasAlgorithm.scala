package dijkstra

import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

/**
 * Created by IntelliJ IDEA.
 * User: jpc
 * Date: 12/19/11
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */

class DijkstrasAlgorithm(aGraph: Graph) {
  val graph: Graph = aGraph
  var settledNodes: Set[Vertex] = Set[Vertex]()
  var unsettledNodes: Set[Vertex] = Set[Vertex]()
  var predecessors: Map[Vertex, Vertex] = Map[Vertex, Vertex]()
  var distances: Map[Vertex, Int] = Map[Vertex, Int]()
  var src: Vertex = null

  // public interface
  def execute(aSrc: Vertex) {
    src = aSrc
    distances += (src -> 0)
    unsettledNodes += src
    while (unsettledNodes.size > 0) {
      val node = getMinimum(unsettledNodes)
      settledNodes += node
      unsettledNodes -= node
      findMinimalDistances(node)
    }
  }

  /*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
  def getPath(aTarget: Vertex) : (Seq[Vertex], Seq[Edge]) = {
    var path = new ListBuffer[Vertex]
    var edges = new ListBuffer[Edge]
    var step = aTarget
    if (!predecessors.contains(step)) {
      return (path, edges)
    }
    path += aTarget
    while (predecessors.contains(step)) {
      val tmp = predecessors.get(step)
      edges += getEdge (tmp.get, step).get
      step = tmp.get
      path += step
    }
    (path.reverse, edges.reverse)
  }

  // Private methods

  private def findMinimalDistances(aNode: Vertex) {
    val adjacentNodes = getNeighbors(aNode)
    adjacentNodes.foreach( target => {
      val shortestDistanceTarget =  getShortestDistance(aNode) + getDistance (aNode, target)
      if (getShortestDistance(target) > shortestDistanceTarget) {
        distances += (target -> shortestDistanceTarget)
        predecessors += (target -> aNode)
        unsettledNodes += target
      }
    })
  }

  private def getEdge(aNode: Vertex,  aTarget: Vertex) : Option[Edge] = {
     val edges = graph.edges
     edges.foreach( edge => {
        if (edge.src.equals(aNode) && edge.dest.equals(aTarget)) return Option(edge)
     })
     None
  }

  private def getDistance(aNode: Vertex,  aTarget: Vertex) : Int = {
     val edges = graph.edges
     edges.foreach( edge => {
        if (edge.src.equals(aNode) && edge.dest.equals(aTarget)) return edge.weight
     })
     throw new RuntimeException("Should not happen")
  }

  private def getNeighbors(aNode: Vertex) : ListBuffer[Vertex] = {
    var neighbors = new ListBuffer[Vertex]()
    val edges = graph.edges;
    edges.foreach( edge => {
      if (edge.src.equals(aNode) && !isSettled(edge.dest)) {
        neighbors += edge.dest
      }
    })
    neighbors
  }

  private def getMinimum(aVertexes: Set[Vertex]): Vertex = {
    var minimum: Vertex = null
    aVertexes.foreach( vertex => {
      if (minimum == null) {
        minimum = vertex
      }
      else {
        if (getShortestDistance(vertex) < getShortestDistance(minimum)) minimum = vertex
      }
    })
    minimum
  }

  private def isSettled(aVertex: Vertex) : Boolean = {
    settledNodes.contains(aVertex)
  }

  private def getShortestDistance(aDest: Vertex): Int = {
    val dist = distances.get(aDest)
    dist.getOrElse(Int.MaxValue)
  }
}