package getco.parsers;

public class EquityDefinitionParserFactory {

  private EquityDefinitionParserFactory() {}

  public static EquityDefinitionParser getEquityDefinitionParser(char delim, String filePath) {
    return new EquityDefinitionParserImpl(delim, filePath);
  }
}