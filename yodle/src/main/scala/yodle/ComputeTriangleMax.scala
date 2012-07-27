package yodle

import scala.io.Source

case class ComputeTriangleMax(val filename:String)
{
  def printArray(a: Array[String]) {
    println(" a: [" + a.deep.mkString(", ") + "]")
  }

  def computeMax = {
    val src = Source.fromURL(getClass.getResource(filename))
    val values = src.getLines().map(_.split(" ")).toArray.flatten  // String[]
    val t = Triangle(values)
    0
    //Triangle(values).max
  }
}
object ComputeTriangleMax
{
  def main(args: Array[String]) {
    val c = ComputeTriangleMax(args(0))
    val max = c.computeMax
    println("Max of triangle in file " + args(0) + " is  " + max)
  }
}
