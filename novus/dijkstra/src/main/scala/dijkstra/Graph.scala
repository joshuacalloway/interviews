package dijkstra


import scala.collection.mutable.ListBuffer

/**
 * Created by IntelliJ IDEA.
 * User: jpc
 * Date: 12/19/11
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */

class Graph(aVertexes: ListBuffer[Vertex], aEdges: ListBuffer[Edge]) {
  val vertexes: ListBuffer[Vertex] = aVertexes
  val edges: ListBuffer[Edge] = aEdges
}