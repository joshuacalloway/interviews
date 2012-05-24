package getco.parsers

import getco.model.EquityDefinition
import getco.model.EquityDefinitionImpl
import java.util.Collection
import java.util.ArrayList

import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source

protected class EquityDefinitionParserImpl(val delim: Char, val filePath: String) extends EquityDefinitionParser
{
  def log = LoggerFactory.getLogger(getClass)
  val normalized = Map("industry_subgroup" -> "subgroup", 
                       "industry_group" -> "group",
                       "industry_sector" -> "sector",
                     "security_category" -> "category",
                     "ticker" -> "symbol")
  
  def parseFile(): Collection[EquityDefinition] = {
    log.info("parseFile version with delim " + delim + " on filePath " + filePath)
    val ret = new ArrayList[EquityDefinition]()
    val iter = Source.fromURL(getClass.getResource(filePath)).getLines().map(_.split(delim))
    //val iter = src.getLines().map(_.split(delim))
    val headersArr = iter.take(1).next   // Array[String]
    val headers = headersArr.zipWithIndex.map{case(value, index) => (value.toLowerCase, index)}.toMap
    
    val normalizedHeaders = headers.map{case(value, index) => ( if (normalized.contains(value)) normalized(value) else value, index)}.toMap

    val nextRow = iter.take(1).next
    val equity = EquityDefinitionImpl(normalizedHeaders, nextRow)
    ret.add(equity)
    //src.close
    ret
  }

}
