package getco.model;

public interface EquityTimeSeries
{
  String getCusip();
  String getTradeDate();
  Double getOpenPrice();
  Double getClosePrice();
  Long   getVolume();

  // these are additional in vendor B
  String getSymbol();
  Double getBid();
  Double getAsk();
  Long getVolumePre();
  Long getVolumePost();

  boolean combineData(final EquityTimeSeries other);
}