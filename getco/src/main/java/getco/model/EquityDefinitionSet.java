package getco.model;

import java.util.Collection;
import java.util.TreeMap;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquityDefinitionSet extends TreeMap<String, EquityDefinition>
{
  private final static Logger log = LoggerFactory.getLogger(EquityDefinitionSet.class);

  @Override public EquityDefinition put(String key, EquityDefinition e) {
    EquityDefinition prevValue = super.put(key, e);
    if (prevValue != null) {
      e.combineData(prevValue);
    }
    return prevValue;
  }

  public void addAll(Collection<EquityDefinition> col) {
    for (EquityDefinition i : col) {
      put (i.getSymbol(), i);
    }
  }
}