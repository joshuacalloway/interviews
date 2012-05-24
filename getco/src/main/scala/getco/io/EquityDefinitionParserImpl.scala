package getco.io

import getco.model.EquityDefinition
import getco.model.EquityDefinitionImpl
import java.util.Collection
import java.util.ArrayList

import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source

protected class EquityDefinitionParserImpl(val delim: Char, val filePath: String) extends EquityDefinitionParser
{
  def log = LoggerFactory.getLogger(getClass)
  val checksum = Map("cusip" -> (8, 1), "isin" -> (11, 1), "sedol" -> (7, 1))  
  // val normalized = Map("industry_subgroup" -> "subgroup", 
  //                      "industry_group" -> "group",
  //                      "industry_sector" -> "sector",
  //                    "security_category" -> "category",
  //                    "ticker" -> "symbol")
  val normalized = Map("ticker" -> "symbol")
  
  def parseFile(): Collection[EquityDefinition] = {
    log.info("parseFile version with delim " + delim + " on filePath " + filePath)
    val ret = new ArrayList[EquityDefinition]()
    val src = Source.fromURL(getClass.getResource(filePath))
    val iter = src.getLines().map(_.split(delim))
    val headersArr = iter.take(1).next   // Array[String]
    val headers = headersArr.zipWithIndex.map{case(value, index) => (value.toLowerCase, index)}.toMap
    
    val normalizedHeaders = headers.map{case(value, index) => ( if (normalized.contains(value)) normalized(value) else value, index)}.toMap
    while (iter.hasNext) {
      ret.add(EquityDefinitionImpl(normalizedHeaders, checksum, iter.next)) 
    }
    src.close
    ret
  }
}
