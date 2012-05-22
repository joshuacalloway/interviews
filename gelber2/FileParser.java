/*
 * Joshua Calloway
 * GelberGroup programming exercise
 * 08/24/2011
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class FileParser {
  static Vector<String> fileNames = new Vector<String>();

  static class ParserThread implements Runnable
  {
    CountingResults.CharacterCountingResults characterResults = new CountingResults.CharacterCountingResults();
    CountingResults.LetterCountingResults letterResults = new CountingResults.LetterCountingResults();
    CountingResults.NumberCountingResults numberResults = new CountingResults.NumberCountingResults();
    CountingResults.WordCountingResults wordResults = new CountingResults.WordCountingResults();

    public ParserThread() {
      characterResults.setMostCommonLimit(3);
      letterResults.setMostCommonLimit(5);
      numberResults.setMostCommonLimit(3);
      wordResults.setMostCommonLimit(10);
    }

    Map<String, CountingResults> getResults() {
      Map<String, CountingResults> results = new HashMap<String, CountingResults>();
      results.put(characterResults.getWhat(), characterResults);
      results.put(letterResults.getWhat(), letterResults);
      results.put(numberResults.getWhat(), numberResults);
      results.put(wordResults.getWhat(), wordResults);
      return results;
    }

    @Override public void run() {
      while (true) {
	String currentFileName;
	synchronized(fileNames) {
	  if (fileNames.size() == 0) {
	    System.out.println("No more files to parse.");
	    break;
	  }
	  currentFileName = (String)fileNames.remove(0);
	}
	parseSingleFile(currentFileName);
      }
    }

    private void parseSingleFile(String currentFileName) {
      try {
	FileInputStream fis = new FileInputStream(currentFileName);
	char currentChar;
	while (fis.available() > 0) {
	  currentChar = (char) fis.read();
	  characterResults.count(currentChar);
	  wordResults.count(currentChar);
	  numberResults.count(currentChar);
	  letterResults.count(currentChar);
	}
      }
      catch (IOException ie) {
	System.out.println("IOException with parsing " + currentFileName);
      }
    
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i <args.length; i++) { fileNames.add(args[i]); }
    
    ParserThread parserA = new ParserThread();
    ParserThread parserB = new ParserThread();

    Thread thread1 = new Thread(parserA);
    thread1.start();
    Thread thread2 = new Thread(parserB);
    thread2.start();
    try {
      thread1.join();
      thread2.join();   // wait for all threads to finish. then merge results
    
      Map<String, CountingResults> resultsA = parserA.getResults();
      Map<String, CountingResults> resultsB = parserB.getResults();
    
      for (Map.Entry<String, CountingResults> entryA : resultsA.entrySet()) {
	String what = entryA.getKey();
	CountingResults valueA = entryA.getValue();
	if (resultsB.containsKey(what)) {
	  CountingResults valueB = resultsB.get(what);
	  valueA = valueA.mergeResults(valueB);
	}
	valueA.printMostCommon();
      }
    } catch (InterruptedException e) {
      System.out.println("Unable to join threads " + e.getMessage());
    }    
  }
}