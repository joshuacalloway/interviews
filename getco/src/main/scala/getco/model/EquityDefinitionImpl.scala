package getco.model

import scala.annotation.target.getter
import scala.reflect.BeanProperty

protected object EquityDefinitionImpl {
  def apply(headers: Map[String, Int], values: Array[String]):EquityDefinitionImpl = {
    val cusip = getValueOrNull(headers, values, "cusip")
    val isin = getValueOrNull(headers, values, "isin")
    val commonNumber = getValueOrNull(headers, values, "common_number")

    val symbol = getValueOrNull(headers, values, "symbol")

    val country = getValueOrNull(headers, values, "country")
    val category = getValueOrNull(headers, values, "category")

    val exchange  = getValueOrNull(headers, values, "exchange")
    val primaryExchange  = getValueOrNull(headers, values, "primary_exchange")
    val sector = getValueOrNull(headers, values, "sector")
    val group = getValueOrNull(headers, values, "group")
    val subgroup = getValueOrNull(headers, values, "subgroup")
    val sedol = getValueOrNull(headers, values, "sedol")

    new EquityDefinitionImpl(cusip, isin, sedol, commonNumber, symbol, country, category, exchange, primaryExchange, sector, group, subgroup)
  }

  def getValueOrNull(headers: Map[String, Int], values: Array[String], item: String) = {
    headers.contains(item) match {
      case true => values(headers(item))
      case false => null
    }
  }
}


protected class EquityDefinitionImpl(
  @BeanProperty val cusip: String, 
  @BeanProperty val isin: String,
  @BeanProperty val sedol: String,
  @BeanProperty val commonNumber: String,
  @BeanProperty val symbol: String, 
  @BeanProperty val country: String, 
  @BeanProperty val category: String,
  @BeanProperty val exchange: String,
  @BeanProperty val primaryExchange: String,
  @BeanProperty val sector: String,
  @BeanProperty val group: String,
  @BeanProperty val subgroup: String

) extends EquityDefinition
{
}
