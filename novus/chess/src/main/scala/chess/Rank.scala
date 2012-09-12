package chess

abstract sealed case class Rank(name:String, value:Int) {

  def minusOne = value match {
    case 2 => Some(one)
    case 3 => Some(two)
    case 4 => Some(three)
    case 5 => Some(four)
    case 6 => Some(five)
    case 7 => Some(six)
    case 8 => Some(seven)
    case _ => None
  }

  def plusOne = value match {
    case 1 => Some(two)
    case 2 => Some(three)
    case 3 => Some(four)
    case 4 => Some(five)
    case 5 => Some(six)
    case 6 => Some(seven)
    case 7 => Some(eight)
    case _ => None
  }
}

object one extends Rank("1",1)
object two extends Rank("2",2)
object three extends Rank("3",3)
object four extends Rank("4",4)
object five extends Rank("5",5)
object six extends Rank("6",6)
object seven extends Rank("7",7)
object eight extends Rank("8",8)

/*
object Rank extends Enumeration {
  type RankType = Value
  val one, two, three, four, five, six, seven, eight = Value
}
*/