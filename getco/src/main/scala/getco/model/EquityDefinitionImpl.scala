package getco.model

import scala.annotation.target.getter
import scala.reflect.BeanProperty

protected object EquityDefinitionImpl extends EquityModelUtils {

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

    val industryGroup = getValueOrNull(headers, checksum, values, "industry_group")
    val industrySubgroup = getValueOrNull(headers, checksum, values, "industry_subgroup")
    val industrySector = getValueOrNull(headers, checksum, values, "industry_sector")
    val securityCategory = getValueOrNull(headers, checksum, values, "security_category")

    new EquityDefinitionImpl(cusip, isin, sedol, commonNumber, symbol, country, category, exchange, primaryExchange, sector, group, subgroup, industryGroup, industrySubgroup, industrySector, securityCategory)
  }
}


protected class EquityDefinitionImpl(
  @BeanProperty val cusip: String, 
  @BeanProperty val isin: String,
  @BeanProperty var sedol: String,
  @BeanProperty var commonNumber: String,
  @BeanProperty val symbol: String, 
  @BeanProperty var country: String, 
  @BeanProperty var category: String,
  @BeanProperty var exchange: String,
  @BeanProperty var primaryExchange: String,
  @BeanProperty var sector: String,
  @BeanProperty var group: String,
  @BeanProperty var subgroup: String,
  @BeanProperty var industryGroup: String,
  @BeanProperty var industrySubgroup: String,
  @BeanProperty var industrySector: String,
  @BeanProperty var securityCategory: String
) extends EquityDefinition
{

  @Override def combineData(other: EquityDefinition): Boolean = {
    if (cusip != other.getCusip ||
        isin != other.getIsin ||
        symbol != other.getSymbol) return false;

    if (category == null) category = other.getCategory
    if (sector == null) sector = other.getSector
    if (group == null) group = other.getGroup
    if (subgroup == null) subgroup = other.getSubgroup

    if (industryGroup == null) industryGroup = other.getIndustryGroup
    if (industrySubgroup == null) industrySubgroup = other.getIndustrySubgroup
    if (industrySector == null) industrySector = other.getIndustrySector
    if (securityCategory == null) securityCategory = other.getSecurityCategory
    if (sedol == null) sedol = other.getSedol
    if (exchange == null) exchange = other.getExchange
    if (primaryExchange == null) primaryExchange = other.getPrimaryExchange
    if (country == null) country = other.getCountry
    if (commonNumber == null) commonNumber = other.getCommonNumber
    true
  }
}
