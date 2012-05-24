Joshua Calloway - Getco code exercise

QUICK start
 0. typescript contains terminal output for running app
 1. getco.EquityMasterApplication is the main app
      bin\run.sh  [ java -jar bin/getco.jar ]
 2. equitydefinition.txt is the Equity Definitions output sorted by symbol
 3. equitytimeseries.txt is the Equity Time Series output sorted traded_date, cusip
 4. application_errors.log contains errors from parsing


DESIGN choices
 A. getco.io package contains all of the Parsers, Writer classes
 B. Parser, Writer classes are created by EquityWriterOrParserFactory
 C. getco.model package contains model classes EquityDefinition, EquityTimeSeries
 D. SanityTestSuite has a few sanity tests for testing the Application

TECHNOLOGY choices
 1. Developed in Java and Scala
 2. Scala for parsing files and writing files 
 3. SBT - simple build tool, like Maven but simple
