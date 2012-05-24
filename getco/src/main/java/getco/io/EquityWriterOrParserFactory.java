package getco.io;

import getco.model.EquityDefinitionSet;
import getco.model.EquityTimeSeriesSet;

public final class EquityWriterOrParserFactory {
  private EquityWriterOrParserFactory() {}

  public static EquityTimeSeriesWriter getEquityTimeSeriesWriter(final EquityTimeSeriesSet set, final String filePath) {
    return new EquityTimeSeriesWriterImpl(set, filePath);
  }

  public static EquityDefinitionWriter getEquityDefinitionWriter(final EquityDefinitionSet set, final String filePath) {
    return new EquityDefinitionWriterImpl(set, filePath);
  }

  public static EquityDefinitionParser getEquityDefinitionParser(final char delim, final String filePath) {
    return new EquityDefinitionParserImpl(delim, filePath);
  }

  public static EquityTimeSeriesParser getEquityTimeSeriesParser(final char delim, final String filePath) {
    return new EquityTimeSeriesParserImpl(delim, filePath);
  }
}