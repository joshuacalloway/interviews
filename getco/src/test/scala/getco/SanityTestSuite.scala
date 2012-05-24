package getco

import org.scalatest.FunSuite

import getco.parsers._
import getco.model._

class SanityTestSuite extends FunSuite {

  test("Simple Sanity Test on vendor A Equity Definition") {
    val parser = getco.parsers.EquityDefinitionParserFactory.getEquityDefinitionParser(',', "/vendor_a_eq.txt")
    val col = parser.parseFile()
    val i = col.iterator()
    val first = i.next
    //037833100,US0378331005,2046251,AAPL,Common,XNAS,Technology,Computers,Computers

    assert("037833100" == first.getCusip())
    assert("US0378331005" == first.getIsin())
    assert("2046251" == first.getSedol())
    assert("AAPL" == first.getSymbol())
    assert("Common" == first.getCategory())
    assert("XNAS" == first.getPrimaryExchange())
    assert(null == first.getExchange())
    assert("Technology" == first.getSector())
    assert("Computers" == first.getGroup())
    assert("Computers" == first.getSubgroup())
    assert(1 == 1)
  }

  test("Simple Sanity Test on vendor B Equity Definition") {
    val parser = getco.parsers.EquityDefinitionParserFactory.getEquityDefinitionParser('|', "/vendor_b_eq.txt")
    val col = parser.parseFile()
    val i = col.iterator()
    val first = i.next
    assert("03783310" == first.getCusip())
    assert("US0378331005" == first.getIsin())
    assert("009718834" == first.getCommonNumber())
    assert("AAPL" == first.getSymbol())
    assert("US" == first.getCountry())
    assert("Common" == first.getCategory())
    assert(null == first.getPrimaryExchange())
    assert("XNGS" == first.getExchange())
    assert("Tech." == first.getSector())
    assert("Computers" == first.getGroup())
    assert("Computers" == first.getSubgroup())
    assert(1 == 1)
  }
}
