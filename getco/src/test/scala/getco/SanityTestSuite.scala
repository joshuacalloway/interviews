package getco

import org.scalatest.FunSuite

import collection.JavaConversions._
import scala.collection.JavaConversions._

import getco.io._
import getco.model._

class SanityTestSuite extends FunSuite {

  test("Simple Sanity Test for EquityModelUtils.parseLong") {
    object Util extends EquityModelUtils {}
    assert(null == Util.parseLong(null))
    assert(null == Util.parseLong(""))
    assert(1000 == Util.parseLong("1000"))
    assert(1000 == Util.parseLong("1000.0"))
    assert(1000 == Util.parseLong("1000.00000"))
    assert(1000 != Util.parseLong("1000.00001"))
    assert(null == Util.parseLong("1000.00001"))
    assert(null == Util.parseLong("AAPL"))
  }

  test("Simple Sanity Test for EquityModelUtils.parseDouble") {
    object Util extends EquityModelUtils {}
    assert(null == Util.parseDouble(null))
    assert(null == Util.parseDouble(""))
    assert(1000 == Util.parseDouble("1000"))
    assert(1000 == Util.parseDouble("1000.0"))
    assert(1000 == Util.parseDouble("1000.00000"))
    assert(1000 != Util.parseDouble("1000.00001"))
    assert(0.65 == Util.parseDouble("000.65"))
    assert(0.65 == Util.parseDouble(".65"))
    assert(1000.00001 == Util.parseDouble("1000.00001"))
    assert(null == Util.parseDouble("AAPL"))
  }


  test("Simple Sanity Test on vendor A Time series") {
    val parser = getco.io.EquityWriterOrParserFactory.getEquityTimeSeriesParser(',', "/vendor_a_ts.txt")
    val col = parser.parseFile()
    val i = col.iterator()
    val first = i.next

    assert("03783310" == first.getCusip)
    assert("2011-11-01" == first.getTradeDate)
    assert(397.41 == first.getOpenPrice)
    assert(396.51 == first.getClosePrice)
    assert(18992411 == first.getVolume)
  }

  test("Simple Sanity Test on vendor B Time series") {
    val parser = getco.io.EquityWriterOrParserFactory.getEquityTimeSeriesParser('|', "/vendor_b_ts.txt")
    val col = parser.parseFile()
    val i = col.iterator()
    val first = i.next

    //AAPL:037833100|2011-11-01|397.410000|396.510000|396.500000|396.510000|166729.000000|310857.000000|18514825.000000
    assert("03783310" == first.getCusip)
    assert("AAPL" == first.getSymbol)
    assert("2011-11-01" == first.getTradeDate)
    assert(397.41 == first.getOpenPrice)
    assert(396.51 == first.getClosePrice)
    assert(18514825 == first.getVolume)
  }

  test("Simple Sanity Test on vendor A Equity Definition") {
    val parser = getco.io.EquityWriterOrParserFactory.getEquityDefinitionParser(',', "/vendor_a_eq.txt")
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
    assert(null == first.getSector())
    assert("Technology" == first.getIndustrySector())
    assert("Computers" == first.getIndustryGroup())
    assert("Computers" == first.getIndustrySubgroup())
    assert(null == first.getGroup())
    assert(null == first.getSubgroup())

  }

  test("Simple Sanity Test on vendor B Equity Definition") {
    val parser = getco.io.EquityWriterOrParserFactory.getEquityDefinitionParser('|', "/vendor_b_eq.txt")
    val col = parser.parseFile()
    val i = col.iterator()
    val first = i.next
    assert("03783310" == first.getCusip())
    assert("US037833100" == first.getIsin())
    assert("009718834" == first.getCommonNumber())
    assert(null == first.getSedol())
    assert("AAPL" == first.getSymbol())
    assert("US" == first.getCountry())
    assert("Common" == first.getSecurityCategory())
    assert(null == first.getCategory())
    assert(null == first.getPrimaryExchange())
    assert("XNGS" == first.getExchange())
    assert("Tech." == first.getSector())
    assert("Computers" == first.getGroup())
    assert("Computers" == first.getSubgroup())
  }


  test("Simple combining of EquitieDefinitions from vendor B and Vendor A") {
    val parserB = getco.io.EquityWriterOrParserFactory.getEquityDefinitionParser('|', "/vendor_b_eq.txt")
    val colB = parserB.parseFile()
    assert(colB.size == 31)
    val parserA = getco.io.EquityWriterOrParserFactory.getEquityDefinitionParser(',', "/vendor_a_eq.txt")
    val colA = parserA.parseFile()
    assert(colA.size == 26)
    val set = new EquityDefinitionSet()
    set.putAll(convertToMap1(colA))
    set.putAll(convertToMap1(colB))
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

  test("Simple combining of EquitieTimeSeries from vendor B and Vendor A") {
    val parserB = getco.io.EquityWriterOrParserFactory.getEquityTimeSeriesParser('|', "/vendor_b_ts.txt")
    val colB = parserB.parseFile()
    assert(colB.size == 308)
    val parserA = getco.io.EquityWriterOrParserFactory.getEquityTimeSeriesParser(',', "/vendor_a_ts.txt")
    val colA = parserA.parseFile()
    assert(colA.size == 285)
    val set = new EquityTimeSeriesSet()
    set.putAll(convertToMap2(colA))
    set.putAll(convertToMap2(colB))
    //println("set.size : " + set.size)
    assert(set.size == 308)

    val aapl = set.get("date: 2011-11-01, cusip: 03783310")
    assert("03783310" == aapl.getCusip)
    assert("AAPL" == aapl.getSymbol)
    assert("2011-11-01" == aapl.getTradeDate)
    assert(397.41 == aapl.getOpenPrice)
    assert(396.51 == aapl.getClosePrice)
    assert(18514825 == aapl.getVolume)
  }

  def convertToMap2(col : Collection[EquityTimeSeries]) : java.util.TreeMap[String, EquityTimeSeries] = {
    val m = new java.util.TreeMap[String,EquityTimeSeries]()
    col.foreach { i => {
      m.put("date: "+ i.getTradeDate() + ", cusip: " + i.getCusip(), i) 
    }
               }
    m
  }

  def convertToMap1(col : Collection[EquityDefinition]) : java.util.TreeMap[String, EquityDefinition] = {
    val m = new java.util.TreeMap[String,EquityDefinition]()
    col.foreach { i => m.put(i.getSymbol(), i) }
    m
  }
}
               
