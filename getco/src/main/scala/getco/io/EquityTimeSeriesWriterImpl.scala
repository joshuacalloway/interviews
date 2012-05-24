package getco.io

import getco.model.EquityTimeSeries
import getco.model.EquityTimeSeriesSet
import java.util.Collection
import java.util.ArrayList
import java.io._

import org.slf4j.{Logger, LoggerFactory}



protected class EquityTimeSeriesWriterImpl(val set: EquityTimeSeriesSet, val filePath: String) extends EquityTimeSeriesWriter
{
  def log = LoggerFactory.getLogger(getClass)

  def writeFile() = {
    val file = new File(filePath)
    log.info("writing EquityTimeSeries file to " + file.getAbsolutePath)
    val writer = new PrintWriter(file)
    val i = set.entrySet.iterator()
    writer.println(getHeaders)
    while (i.hasNext) {
      writer.println(toCommaDelimited(i.next.getValue))
    }
    writer.close
  }

  private def getHeaders(): String = {
    "TradeDate|Cusip|Symbol|OpenPrice|ClosePrice|Bid|Ask|Volume|VolumePre|VolumePost"
  }

  private def toCommaDelimited(e: EquityTimeSeries): String = {
    (e.getTradeDate()
     +"|"+e.getCusip()
     +"|"+e.getSymbol()
     +"|"+e.getOpenPrice()
     +"|"+e.getClosePrice()
     +"|"+e.getBid()
     +"|"+e.getAsk()
     +"|"+e.getVolume()
     +"|"+e.getVolumePre()
     +"|"+e.getVolumePost())
  }

}
