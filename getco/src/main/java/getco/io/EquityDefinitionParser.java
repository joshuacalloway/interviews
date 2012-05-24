package getco.io;

import getco.model.EquityDefinition;
import java.util.Collection;

public abstract class EquityDefinitionParser
{
  protected EquityDefinitionParser() {}
  public abstract Collection<EquityDefinition> parseFile();
}