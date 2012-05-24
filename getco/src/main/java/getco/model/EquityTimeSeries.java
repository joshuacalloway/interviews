package getco.model;


public interface EquityTimeSeries
{
  String getCusip();
  String getTradeDate();
  String getOpenPrice();
  String getClosePrice();
  String getVolume();

  // these are additional in vendor B
  String getSymbol();
  String getBid();
  String getAsk();
  String getVolumePre();
  String getVolumePost();

  boolean combineData(final EquityTimeSeries other);
}