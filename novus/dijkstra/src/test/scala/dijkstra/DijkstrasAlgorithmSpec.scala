package dijkstra

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter


import scala.collection.mutable.ListBuffer

/**
 * Created by IntelliJ IDEA.
 * User: jpc
 * Date: 12/19/11
 * Time: 6:25 PM
 * To change this template use File | Settings | File Templates.
 */

class DijkstrasAlgorithmSpec extends FunSuite with BeforeAndAfter {

  var graph: Graph = null
  var dijkstra : DijkstrasAlgorithm = null
  var node0: Vertex = null
  var node1: Vertex = null
  var node2: Vertex = null
  var node3: Vertex = null
  var node4: Vertex = null
  var node5: Vertex = null
  var node6: Vertex = null
  var node7: Vertex = null
  var node8: Vertex = null
  var node9: Vertex = null
  var node10: Vertex = null
  var node11: Vertex = null

  test("dijkstras shortest path from node0 to node 10") {
    dijkstra.execute(node0)
    val (path, edges) = dijkstra.getPath(node10)
    assert(path(0).equals(node0))
    assert(path(1).equals(node2))
    assert(path(2).equals(node7))
    assert(path(3).equals(node9))
    assert(path(4).equals(node10))

    assert(edges(0).weight == 217)
    assert(edges(1).weight == 103)
    assert(edges(2).weight == 167)
    assert(edges(3).weight == 40)
  }

  test("dijkstras shortest path from node0 to node5 does not exist") {
     dijkstra.execute(node0)
     val (path, edges) = dijkstra.getPath(node5)
     assert(path.size == 0)
     assert(edges.size == 0)
  }

  before {
    buildDijkstrasGraph
  }
  after {
  }

  def buildDijkstrasGraph() = {
    var nodes = ListBuffer[Vertex]()
    node0 = new Vertex(0,"Node_0")
    nodes += node0
    node1 = new Vertex(1, "Node_1")
    nodes += node1
    node2 = new Vertex(2, "Node_2")
    nodes += node2
    node3 = new Vertex(3, "Node_3")
    nodes += node3
    node4 = new Vertex(4, "Node_4")
    nodes += node4
    node5 = new Vertex(5, "Node_5")
    nodes += node5
    node6 = new Vertex(6, "Node_6")
    nodes += node6
    node7 = new Vertex(7, "Node_7")
    nodes += node7
    node8 = new Vertex(8, "Node_8")
    nodes += node8
    node9 = new Vertex(9, "Node_9")
    nodes += node9
    node10 = new Vertex(10, "Node_10")
    nodes += node10
    node11 = new Vertex(11, "Node_11")
    nodes += node11

    var edges = ListBuffer[Edge]()
    edges += new Edge(0, node0, node1, 85)
    edges += new Edge(1, node0, node2, 217)
    edges += new Edge(2, node0, node4, 173)
    edges += new Edge(3, node2, node6, 186)
    edges += new Edge(4, node2, node7, 103)
    edges += new Edge(5, node3, node7, 183)
    edges += new Edge(6, node5, node8, 250)
    edges += new Edge(7, node8, node9, 84)
    edges += new Edge(8, node7, node9, 167)
    edges += new Edge(9, node4, node9, 502)
    edges += new Edge(10, node9, node10, 40)
    edges += new Edge(11, node1, node10, 600)

    graph = new Graph(nodes, edges)
    dijkstra = new DijkstrasAlgorithm(graph)
  }
}