package getco

import org.scalatest.FunSuite

import collection.JavaConversions._
import scala.collection.JavaConversions._

import getco.io._
import getco.model._

class SanityTestSuite extends FunSuite {

  test("Simple Sanity Test on vendor A Equity Definition") {
    val parser = getco.io.EquityDefinitionIOFactory.getEquityDefinitionParser(',', "/vendor_a_eq.txt")
    val col = parser.parseFile()
    val i = col.iterator()
    val first = i.next
    //037833100,US0378331005,2046251,AAPL,Common,XNAS,Technology,Computers,Computers

    assert("03783310" == first.getCusip())
    assert("US037833100" == first.getIsin())
    assert(null == first.getCommonNumber())
    assert("2046251" == first.getSedol())
    assert("AAPL" == first.getSymbol())
    assert(null == first.getCountry())
    assert("Common" == first.getCategory())
    assert("XNAS" == first.getPrimaryExchange())
    assert(null == first.getExchange())
    assert("Technology" == first.getSector())
    assert("Computers" == first.getGroup())
    assert("Computers" == first.getSubgroup())
  }

  test("Simple Sanity Test on vendor B Equity Definition") {
    val parser = getco.io.EquityDefinitionIOFactory.getEquityDefinitionParser('|', "/vendor_b_eq.txt")
    val col = parser.parseFile()
    val i = col.iterator()
    val first = i.next
    assert("03783310" == first.getCusip())
    assert("US037833100" == first.getIsin())
    assert("009718834" == first.getCommonNumber())
    assert(null == first.getSedol())
    assert("AAPL" == first.getSymbol())
    assert("US" == first.getCountry())
    assert("Common" == first.getCategory())
    assert(null == first.getPrimaryExchange())
    assert("XNGS" == first.getExchange())
    assert("Tech." == first.getSector())
    assert("Computers" == first.getGroup())
    assert("Computers" == first.getSubgroup())
  }


  test("Simple combining of EquitieDefinitions from vendor B and Vendor A") {
    val parserB = getco.io.EquityDefinitionIOFactory.getEquityDefinitionParser('|', "/vendor_b_eq.txt")
    val colB = parserB.parseFile()
    assert(colB.size == 31)
    val parserA = getco.io.EquityDefinitionIOFactory.getEquityDefinitionParser(',', "/vendor_a_eq.txt")
    val colA = parserA.parseFile()
    assert(colA.size == 26)
    val set = new EquityDefinitionSet()
    set.putAll(convertToMap(colA))
    set.putAll(convertToMap(colB))
    println("set.size() : " + set.size())
    assert(set.size == 34)

    val aapl = set.get("AAPL")
    assert("03783310" == aapl.getCusip())
    assert("US037833100" == aapl.getIsin())
    assert("009718834" == aapl.getCommonNumber())
    assert("2046251" == aapl.getSedol())
    assert("AAPL" == aapl.getSymbol())
    assert("US" == aapl.getCountry())
    assert("Common" == aapl.getCategory())
    assert("XNAS" == aapl.getPrimaryExchange())
    assert("XNGS" == aapl.getExchange())
    assert("Tech." == aapl.getSector())
    assert("Computers" == aapl.getGroup())
    assert("Computers" == aapl.getSubgroup())
  }


  def convertToMap(col : Collection[EquityDefinition]) : java.util.TreeMap[String, EquityDefinition] = {
    val m = new java.util.TreeMap[String,EquityDefinition]()
    col.foreach { i => m.put(i.getSymbol(), i) }
    m
  }
}
