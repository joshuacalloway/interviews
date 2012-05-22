package dijkstra

/**
 * Created by IntelliJ IDEA.
 * User: jpc
 * Date: 12/19/11
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */

class Edge( aId: Int, aSrc: Vertex,  aDest: Vertex, aWeight: Int) {
  val id: Int = aId
  val src: Vertex = aSrc
  val dest: Vertex = aDest
  val weight: Int = aWeight


}