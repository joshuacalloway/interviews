package getco.io;

import getco.model.EquityTimeSeries;
import java.util.Collection;

public abstract class EquityTimeSeriesParser
{
  protected EquityTimeSeriesParser() {}
  public abstract Collection<EquityTimeSeries> parseFile();
}