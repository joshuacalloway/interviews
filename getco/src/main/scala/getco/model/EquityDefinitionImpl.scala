package getco.model

import scala.annotation.target.getter
import scala.reflect.BeanProperty
import org.slf4j.{Logger, LoggerFactory}

protected object EquityDefinitionImpl {
  def log = LoggerFactory.getLogger(getClass)

  def apply(headers: Map[String, Int], checksum: Map[String, (Int, Int)], values: Array[String]):EquityDefinitionImpl = {
    val cusip = getValueOrNull(headers, checksum, values, "cusip")
    val isin = getValueOrNull(headers, checksum, values, "isin")
    val commonNumber = getValueOrNull(headers, checksum, values, "common_number")

    val symbol = getValueOrNull(headers, checksum, values, "symbol")

    val country = getValueOrNull(headers, checksum, values, "country")
    val category = getValueOrNull(headers, checksum, values, "category")

    val exchange  = getValueOrNull(headers, checksum, values, "exchange")
    val primaryExchange  = getValueOrNull(headers, checksum, values, "primary_exchange")
    val sector = getValueOrNull(headers, checksum, values, "sector")
    val group = getValueOrNull(headers, checksum, values, "group")
    val subgroup = getValueOrNull(headers, checksum, values, "subgroup")
    val sedol = getValueOrNull(headers, checksum, values, "sedol")

    new EquityDefinitionImpl(cusip, isin, sedol, commonNumber, symbol, country, category, exchange, primaryExchange, sector, group, subgroup)
  }

  def getValueOrNull(headers: Map[String, Int], checksum: Map[String, (Int, Int)], values: Array[String], item: String) = {
    
    headers.contains(item) match {
      case true => {
        // log.info("values.size: " + values.size)
        // log.info("headers(item): " + headers(item))
        if (values.size <= headers(item)) {
          log.error("Was not able to parse value '" + item + "' from record '" + values.mkString(",") + "'")
          null
        }
        else if (checksum.contains(item)) values(headers(item)).substring(0, checksum(item)._1)  else values(headers(item))
      }
      case false => null
    }
  }
}


protected class EquityDefinitionImpl(
  @BeanProperty val cusip: String, 
  @BeanProperty val isin: String,
  @BeanProperty var sedol: String,
  @BeanProperty var commonNumber: String,
  @BeanProperty val symbol: String, 
  @BeanProperty var country: String, 
  @BeanProperty val category: String,
  @BeanProperty var exchange: String,
  @BeanProperty var primaryExchange: String,
  @BeanProperty val sector: String,
  @BeanProperty val group: String,
  @BeanProperty val subgroup: String
) extends EquityDefinition
{

  @Override def combineData(other: EquityDefinition) : Boolean = {
    if (sedol == null) sedol = other.getSedol
    if (exchange == null) exchange = other.getExchange
    if (primaryExchange == null) primaryExchange = other.getPrimaryExchange
    if (country == null) country = other.getCountry
    if (commonNumber == null) commonNumber = other.getCommonNumber
    true
  }
}
