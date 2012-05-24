package getco.model

import scala.annotation.target.getter
import scala.reflect.BeanProperty
import org.slf4j.{Logger, LoggerFactory}

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
    val openPrice = getValueOrNull(headers, checksum, values, "open_price")
    val closePrice = getValueOrNull(headers, checksum, values, "close_price")
    val volume = getValueOrNull(headers, checksum, values, "volume")
    val bid  = getValueOrNull(headers, checksum, values, "bid")
    val ask  = getValueOrNull(headers, checksum, values, "ask")
    val volumePre = getValueOrNull(headers, checksum, values, "volumePre")
    val volumePost = getValueOrNull(headers, checksum, values, "volumePost")
    
    new EquityTimeSeriesImpl(cusip,symbol,tradeDate,openPrice,closePrice,volume,bid,ask,volumePre,volumePost)
  }
}

protected class EquityTimeSeriesImpl(
  @BeanProperty val cusip: String,
  @BeanProperty var symbol: String,
  @BeanProperty val tradeDate: String,
  @BeanProperty val openPrice: String,
  @BeanProperty val closePrice: String,
  @BeanProperty val volume: String,
  @BeanProperty var bid: String,
  @BeanProperty var ask: String,
  @BeanProperty var volumePre: String,
  @BeanProperty var volumePost: String
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
    if (bid == null) bid = other.getBid
    if (ask == null) ask = other.getAsk
    if (volumePre == null) volumePre = other.getVolumePre
    if (volumePost == null) volumePost = other.getVolumePost
    true
  }
}
