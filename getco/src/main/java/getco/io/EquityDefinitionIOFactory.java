package getco.io;

import getco.model.EquityDefinitionSet;

public final class EquityDefinitionIOFactory {

  private EquityDefinitionIOFactory() {}

  public static EquityDefinitionWriter getEquityDefinitionWriter(final EquityDefinitionSet set, final String filePath) {
    return new EquityDefinitionWriterImpl(set, filePath);
  }

  public static EquityDefinitionParser getEquityDefinitionParser(final char delim, final String filePath) {
    return new EquityDefinitionParserImpl(delim, filePath);
  }
}