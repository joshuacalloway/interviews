package getco.model;


public interface EquityDefinition
{
  String getCusip();
  String getIsin();
  String getSedol();
  String getCommonNumber();
  String getSymbol();
  String getCountry();
  String getCategory();
  String getExchange();
  String getPrimaryExchange();
  String getSector();
  String getGroup();
  String getSubgroup();
  String getIndustryGroup();
  String getIndustrySubgroup();
  String getIndustrySector();
  String getSecurityCategory();

  boolean combineData(final EquityDefinition other);
  //cusip,isin,sedol,ticker,category,primary_exchange,industry_sector,industry_group,industry_subgroup
}