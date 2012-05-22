/*
 * Joshua Calloway
 * GelberGroup programming exercise
 * 08/24/2011
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
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

public class URLParser {
  static Vector<String> urlNames = new Vector<String>();

  static class ParserThread implements Runnable
  {
    CountingResults.CharacterCountingResults characterResults = new CountingResults.CharacterCountingResults();
    CountingResults.LetterCountingResults letterResults = new CountingResults.LetterCountingResults();
    CountingResults.NumberCountingResults numberResults = new CountingResults.NumberCountingResults();
    CountingResults.WordCountingResults wordResults = new CountingResults.WordCountingResults();

    public ParserThread() {
      characterResults.setMostCommonLimit(2);
      letterResults.setMostCommonLimit(2);
      numberResults.setMostCommonLimit(2);
      wordResults.setMostCommonLimit(2);
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
	String currentURL;
	synchronized(urlNames) {
	  if (urlNames.size() == 0) {
	    System.out.println("No more URLs to parse.");
	    break;
	  }
	  currentURL = (String)urlNames.remove(0);
	}
	parseSingleURL(currentURL);
      }
    }

    private String stripMetaTags(String html) {
      html = html.replaceAll("\\<script.*?\\</script>","");
      html = html.replaceAll("\\<style.*?\\</style>","");
      html = html.replaceAll("\\<.*?>","");
      html = html.replaceAll("&nbsp;"," ");
      html = html.replaceAll("&amp;"," ");
      html = html.replaceAll("&#39;"," ");
      html = html.replaceAll("&lrm;"," ");
      html = html.replaceAll("&raquo;"," ");
      html = html.replaceAll("&quot;"," ");
      html = html.replaceAll("&#9660;"," ");
      return html;
    }

    private void parseSingleURL(String currentURL) {
      try {
	URL url = new URL(currentURL);
	BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	String inputLine;
	StringBuffer content= new StringBuffer();
	while ((inputLine = in.readLine()) != null) {
	  content.append(inputLine);
	}
	in.close();
	String strippedContent = stripMetaTags(content.toString());
	StringReader reader = new StringReader(strippedContent);
	int i;
	while ((i = reader.read()) != -1) {
	  char currentChar = (char)i;
	  characterResults.count(currentChar);
	  wordResults.count(currentChar);
	  numberResults.count(currentChar);
	  letterResults.count(currentChar);
	}
      }
      catch (IOException ie) {
	System.out.println("IOException with parsing " + currentURL);
      }
    
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i <args.length; i++) { urlNames.add(args[i]); }
    
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