package getco.model

import scala.reflect.BeanProperty
import org.slf4j.{Logger, LoggerFactory}
import java.lang.{Double, Long}

protected object EquityTimeSeriesImpl extends EquityModelUtils {

  def apply(headers: Map[String, Int], checksum: Map[String, (Int, Int)], values: Array[String]):EquityTimeSeriesImpl = {
    var cusip : String = null
    var symbol : String = null
    val id = getValueOrNull(headers, checksum, values, "id")
    id match {
      case null => cusip = getValueOrNull(headers, checksum, values, "cusip")
      case _ => {
        cusip = getValueWithoutChecksum(checksum, "cusip", id.split(':')(1))
        symbol = id.split(':')(0)    
      }
    }
    val tradeDate = getValueOrNull(headers, checksum, values, "trade_date")
    val openPrice = parseDouble(getValueOrNull(headers, checksum, values, "open_price"))
    val closePrice = parseDouble(getValueOrNull(headers, checksum, values, "close_price"))
    val volume = parseLong(getValueOrNull(headers, checksum, values, "volume"))
    val bid  = parseDouble(getValueOrNull(headers, checksum, values, "bid"))
    val ask  = parseDouble(getValueOrNull(headers, checksum, values, "ask"))
    val volumePre = parseLong(getValueOrNull(headers, checksum, values, "volumePre"))
    val volumePost = parseLong(getValueOrNull(headers, checksum, values, "volumePost"))
    
    new EquityTimeSeriesImpl(cusip,symbol,tradeDate,openPrice,closePrice,volume,bid,ask,volumePre,volumePost)
  }
}

protected class EquityTimeSeriesImpl(
  @BeanProperty val cusip: String,
  @BeanProperty var symbol: String,
  @BeanProperty val tradeDate: String,
  @BeanProperty val openPrice: java.lang.Double,
  @BeanProperty val closePrice: java.lang.Double,
  @BeanProperty val volume: java.lang.Long,
  @BeanProperty var bid: java.lang.Double,
  @BeanProperty var ask: java.lang.Double,
  @BeanProperty var volumePre: java.lang.Long,
  @BeanProperty var volumePost: java.lang.Long
) extends EquityTimeSeries
{
  def log = LoggerFactory.getLogger(getClass)

  @Override def combineData(other: EquityTimeSeries) : Boolean = {
    if (volume != other.getVolume ||
        cusip != other.getCusip ||
        tradeDate != getTradeDate ||
        openPrice != getOpenPrice ||
        closePrice != other.getClosePrice) {
      log.error("Unable to combine time series for " + cusip + " due to ["+volume+"|"+cusip+"|"+tradeDate+"|"+openPrice+"|"+closePrice+"] != ["+other.getVolume + "|"+other.getCusip+"|"+other.getTradeDate+"|"+other.getOpenPrice+"|"+other.getClosePrice+"]"
)
      return false;
    }
    if (symbol == null) symbol = other.getSymbol
    if (bid == 0) bid = other.getBid
    if (ask == 0) ask = other.getAsk
    if (volumePre == null) volumePre = other.getVolumePre
    if (volumePost == null) volumePost = other.getVolumePost
    true
  }
}
