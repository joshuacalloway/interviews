package getco.io

import getco.model.EquityDefinition
import getco.model.EquityDefinitionSet
import java.util.Collection
import java.util.ArrayList
import java.io._

import org.slf4j.{Logger, LoggerFactory}

protected class EquityDefinitionWriterImpl(val set: EquityDefinitionSet, val filePath: String) extends EquityDefinitionWriter
{
  def log = LoggerFactory.getLogger(getClass)

  def writeFile() = {
    val file = new File(filePath)
    log.info("writing EquityDefinition file to " + file.getAbsolutePath)
    val writer = new PrintWriter(file)
    val i = set.entrySet.iterator()
    writer.println(getHeaders)
    while (i.hasNext) {
      writer.println(toCommaDelimited(i.next.getValue))
    }
    writer.close
  }

  private def getHeaders(): String = {
    "symbol|Cusip|Sedol|CommonNumber|Symbol|Country|Category|Exchange|PrimaryExchange|Sector|Group|Subgroup"
  }

  private def toCommaDelimited(e: EquityDefinition): String = {
    (e.getSymbol() 
     +"|"+e.getCusip()
     +"|"+e.getSedol()
     +"|"+e.getCommonNumber()
     +"|"+e.getSymbol()
     +"|"+e.getCountry()
     +"|"+e.getCategory()
     +"|"+e.getExchange()
     +"|"+e.getPrimaryExchange()
     +"|"+e.getSector()
     +"|"+e.getGroup()
     +"|"+e.getSubgroup())
  }

}
