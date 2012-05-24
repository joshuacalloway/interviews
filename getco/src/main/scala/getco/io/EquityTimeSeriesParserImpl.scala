package getco.io

import getco.model.EquityTimeSeries
import getco.model.EquityTimeSeriesImpl
import java.util.Collection
import java.util.ArrayList

import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source

protected class EquityTimeSeriesParserImpl(val delim: Char, val filePath: String) extends EquityTimeSeriesParser
{
  def log = LoggerFactory.getLogger(getClass)
  val checksum = Map("cusip" -> (8, 1))
  val normalized = Map("open" -> "open_price", 
                       "close" -> "close_price")
  
  def parseFile(): Collection[EquityTimeSeries] = {
    log.info("parseFile version with delim " + delim + " on filePath " + filePath)
    val ret = new ArrayList[EquityTimeSeries]()
    val src = Source.fromURL(getClass.getResource(filePath))
    val iter = src.getLines().map(_.split(delim))
    val headersArr = iter.take(1).next   // Array[String]
    val headers = headersArr.zipWithIndex.map{case(value, index) => (value.toLowerCase, index)}.toMap
    
    val normalizedHeaders = headers.map{case(value, index) => ( if (normalized.contains(value)) normalized(value) else value, index)}.toMap

    while (iter.hasNext) {
      ret.add(EquityTimeSeriesImpl(normalizedHeaders, checksum, iter.next)) 
    }
    src.close
    ret
  }

}
