package yodle.tri

// Joshua Calloway
// joshua.calloway@gmail.com
// July 27, 2012
// Yodle Triangle Interview Puzzle

import scala.io.Source

case class ComputeTriangleMax(val filename:String)
{
  def printArray[T](a: Array[T]) {
    println(" a: [" + a.deep.mkString(", ") + "]")
  }

  private def maxValue(a:Int, b:Int) = if (a > b) a else b

  private def addGuardOverFlow(a:Int, b:Int) : Int = {
    val sum = a+b;
    if (sum > a) sum else throw new Exception("Overflow detected when adding " + a + " and " + b)
  }

  private def calculateDepth[T](value: Array[T]) = {
    var i = 1
    var sum = 0
    while (sum < value.length) {
      sum += i;
      i += 1;
    }
    i-1
  }

  def computeMax = {
    val src = Source.fromURL(getClass.getResource(filename))
    val values = src.getLines().map(_.split(" ")).toArray.flatten.map(X => X.toInt)  // Int[]
    val depth = calculateDepth(values)
    var endIndex = values.length
    var currentRow = depth
    var aboveCurrentRow = currentRow - 1

    do {
      var currentRowStart = endIndex - currentRow
      var aboveCurrentRowStart = currentRowStart - aboveCurrentRow
    
      var i = aboveCurrentRowStart
      while (i < aboveCurrentRowStart + aboveCurrentRow) {
	values(i) = addGuardOverFlow(maxValue( values(i+aboveCurrentRow), values(i+aboveCurrentRow+1)),values(i))
	i = i + 1
      }
      endIndex = endIndex - currentRow
      currentRow = currentRow - 1
      aboveCurrentRow = currentRow - 1
    } while ( aboveCurrentRow >= 1)
    values(0)
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
