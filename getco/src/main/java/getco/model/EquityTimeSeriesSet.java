package getco.model;

import java.util.Collection;
import java.util.TreeMap;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquityTimeSeriesSet extends TreeMap<String, EquityTimeSeries>
{
  private final static Logger log = LoggerFactory.getLogger(EquityTimeSeriesSet.class);

  @Override public EquityTimeSeries put(String key, EquityTimeSeries e) {
    EquityTimeSeries prevValue = super.put(key, e);
    if (prevValue != null) {
      e.combineData(prevValue);
    }
    return prevValue;
  }

  public void addAll(Collection<EquityTimeSeries> col) {
    for (EquityTimeSeries i : col) {
      put ("date: " +i.getTradeDate()+", cusip: " +i.getCusip(), i);
    }
  }
}