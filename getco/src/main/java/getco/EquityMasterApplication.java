
package getco;
import java.util.Collection;

import getco.io.EquityTimeSeriesWriter;
import getco.io.EquityDefinitionWriter;
import getco.io.EquityTimeSeriesParser;
import getco.io.EquityDefinitionParser;
import getco.io.EquityWriterOrParserFactory;

import getco.model.EquityDefinitionSet;
import getco.model.EquityTimeSeriesSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquityMasterApplication
{
  static final Logger log = LoggerFactory.getLogger(EquityMasterApplication.class);

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    log.info("EquityMasterApplication version 1.0");

    EquityDefinitionParser parserA = EquityWriterOrParserFactory.getEquityDefinitionParser(',', "/vendor_a_eq.txt");
    EquityDefinitionParser parserB = EquityWriterOrParserFactory.getEquityDefinitionParser('|', "/vendor_b_eq.txt");
    EquityTimeSeriesParser parserC = EquityWriterOrParserFactory.getEquityTimeSeriesParser(',', "/vendor_a_ts.txt");
    EquityTimeSeriesParser parserD = EquityWriterOrParserFactory.getEquityTimeSeriesParser('|', "/vendor_b_ts.txt");
    
    Collection colA = parserA.parseFile();
    Collection colB = parserB.parseFile();
    Collection colC = parserC.parseFile();
    Collection colD = parserD.parseFile();

    EquityDefinitionSet definitionSet = new EquityDefinitionSet();
    definitionSet.addAll(colA);
    definitionSet.addAll(colB);
    EquityTimeSeriesSet timeSeriesSet = new EquityTimeSeriesSet();
    timeSeriesSet.addAll(colC);
    timeSeriesSet.addAll(colD);

    EquityDefinitionWriter equityDefinitionWriter = EquityWriterOrParserFactory.getEquityDefinitionWriter(definitionSet, "equitydefinition.txt");
    EquityTimeSeriesWriter equityTimeSeriesWriter = EquityWriterOrParserFactory.getEquityTimeSeriesWriter(timeSeriesSet, "equitytimeseries.txt");
    equityDefinitionWriter.writeFile();
    equityTimeSeriesWriter.writeFile();

    long timeMillis = System.currentTimeMillis() - start;
    log.info("Finished in " + timeMillis + " milliseconds.");
  }
}