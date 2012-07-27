package yodle

case class Triangle(value: Int, left: Option[Triangle], right: Option[Triangle])
{
  private def maxValue(a:Int, b:Int) = if (a > b) a else b

  private def addGuardOverFlow(a:Int, b:Int) = {
    val sum = a+b;
    if (sum > a) sum else throw new Exception("Overflow detected when adding " + a + " and " + b)
  }
  def max() : Int = (left,right) match {
    case (Some(leftTriangle), Some(rightTriangle)) => addGuardOverFlow(value, maxValue(leftTriangle.max(), rightTriangle.max()))
    case _ => value
  }

}
object Triangle 
{

  def apply(value: Int) : Triangle = new Triangle(value, null, null)
  def apply(value: Int, left: Int, right: Int) : Triangle = new Triangle(value, Some(Triangle(left)), Some(Triangle(right)))
  def apply(value: Int, left: Triangle, right: Triangle) : Triangle = new Triangle(value, Some(left), Some(right))


  // String constructors
  def apply(value: String) : Triangle = Triangle(value.toInt)
  def apply(value: String, left: String, right:String) : Triangle = Triangle(value.toInt, left.toInt, right.toInt)

  def apply(value: String, left: Triangle, right: Triangle) : Triangle = new Triangle(value.toInt, Some(left), Some(right))


  // Array[String] constructor
  def apply(value: Array[String]) : Triangle = createFromArray(value,1)

  private def createFromArray(value: Array[String], depth: Int) : Triangle = value match {
    case Array(x) => Triangle(x)
    case Array(x, _*) if value.length <= depth => Triangle(x)
    case Array(root, _*) => Triangle(root, createFromArray( value.drop(depth), depth+1), createFromArray(value.drop(depth+1), depth+1))
    case _ => throw new Exception("Don't know how to create Triangle from " + value.deep.mkString(", "))
  }


// maybe more efficient to do iteratively
/*
  private def createFromArrayIter(value: Array[String]) : Triangle = {
    var l = List[Triangle]();
    var i = 0
    while (value.length > 0) {
      
    }
//    value.foldLeft((l,i)) ( ((list,index), whatsLeftValue) =>   )
  }
*/

} 
