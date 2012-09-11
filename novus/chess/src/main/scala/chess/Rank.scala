package chess

abstract sealed class Rank(val name:String)

case object one extends Rank("1")
case object two extends Rank("2")
case object three extends Rank("3")
case object four extends Rank("4")
case object five extends Rank("5")
case object six extends Rank("6")
case object seven extends Rank("7")
case object eight extends Rank("8")

/*
object Rank extends Enumeration {
  type RankType = Value
  val one, two, three, four, five, six, seven, eight = Value
}
*/