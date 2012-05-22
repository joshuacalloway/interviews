package dijkstra

/**
 * Created by IntelliJ IDEA.
 * User: jpc
 * Date: 12/19/11
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */

class Vertex( aId: Int, aName: String) {
  val id: Int = aId
  val name: String = aName

  override def toString() : String = {
    name + "("+id+")"
  }

  override def equals(that: Any) : Boolean = {
    that.isInstanceOf[Vertex] && that.asInstanceOf[Vertex].id.equals(id)
  }

  override def hashCode = id.hashCode

}