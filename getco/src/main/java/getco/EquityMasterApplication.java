
package getco;
import java.util.Collection;

import getco.io.EquityDefinitionIOFactory;
import getco.io.EquityDefinitionParser;
import getco.io.EquityDefinitionWriter;
import getco.model.EquityDefinitionSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquityMasterApplication
{
  static final Logger log = LoggerFactory.getLogger(EquityMasterApplication.class);


  public static void main(String[] args) {
    log.info("EquityMasterApplication version 1.0");

    EquityDefinitionParser parserA = EquityDefinitionIOFactory.getEquityDefinitionParser(',', "/vendor_a_eq.txt");
    EquityDefinitionParser parserB = EquityDefinitionIOFactory.getEquityDefinitionParser('|', "/vendor_b_eq.txt");
    
    Collection colA = parserA.parseFile();
    Collection colB = parserB.parseFile();
    EquityDefinitionSet set = new EquityDefinitionSet();
    set.addAll(colA);
    set.addAll(colB);

    EquityDefinitionWriter equityWriter = EquityDefinitionIOFactory.getEquityDefinitionWriter(set, "equitydefinition.txt");
    equityWriter.writeFile();

  }

}