// Joshua Calloway
// 10/11/2010
// For interview with Gelber

package gelber;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// - Read in any number of files text files.
// - Calculate 5 most commonly used letters
// - Calculate 3 most commonly used characters
// - Calculate 3 most commonly used numbers
// - Calculate 10 most commonly used words
public class Problem1 {
    
  static class ParserResult {
    String outputMsg;
    String[] tokens;
    int count;
    String filename;

    public ParserResult(String filename, int count, String outputMsg) {
      this.outputMsg = outputMsg;
      this.count = count;
      this.filename = filename;
      tokens = new String[count];
    }
    
    public void printOut() {
      System.out.print("For file " + filename + ", ");
      System.out.print(outputMsg);
      for (int i = 0; i < tokens.length; i++) {
        if (tokens[i] == null) break;
        System.out.print(tokens[i]);
        System.out.print(", ");
      }
      System.out.println("");
    }
  }

  static class RegexpParser {
    String regexp;
    String outputMsg;
    int count;

    private RegexpParser() {}
    public RegexpParser(String regexp, int count, String outputMsg) {
      this.regexp = regexp;
      this.outputMsg = outputMsg;
      this.count = count;
    }

    public ParserResult parse(String filename) {
      ParserResult result = new ParserResult(filename, count, outputMsg);
      try {
        int maxCount=0;
        Scanner scanner = new Scanner(new File(filename));
        Map<String,Integer> hist = new TreeMap<String,Integer>();
        while (true) {
          String current = scanner.findWithinHorizon(regexp, 0);
          if (current == null) break;
          if (hist.get(current) == null) {
            hist.put(current, 0);
          }
          int currentCount = hist.get(current);
          hist.put(current, currentCount + 1);
          if (currentCount + 1 > maxCount) {
            maxCount = currentCount + 1;
          }
        }
        int numDigitsInMaxCount = Integer.toString(maxCount).length();
        List<String> encodedList = new ArrayList<String>();   // <count-formated>-word -- ie  003-hello
        for (Map.Entry<String, Integer> entry: hist.entrySet()) {
          String current = entry.getKey();
          Integer count = entry.getValue();
          encodedList.add(String.format("%0"+numDigitsInMaxCount+"d", count) + "-"+current);
        }
        Collections.sort(encodedList, Collections.reverseOrder());
        Iterator<String> j = encodedList.iterator();
        for (int i = 0; i < result.tokens.length; i++) {
          if (!j.hasNext()) break;
          String encoded = j.next();
          StringTokenizer tkzr = new StringTokenizer(encoded, "-");
          tkzr.nextToken();  // throw away encoded part
          result.tokens[i] = tkzr.nextToken();  
        }
        return result;
      }
      catch (FileNotFoundException fe) {
        String error = "Error in parsing file " + filename +" " + fe.getMessage();
        System.err.println(error);
        throw new RuntimeException(error);
      }
    }
  }
 
  static class CompleteParser implements Runnable {
    RegexpParser letterParser = new RegexpParser("([a-zA-Z])", 5, "five most commonly used letters: ");
    RegexpParser charParser = new RegexpParser("(.)", 3, "three most commonly used characters: ");
    RegexpParser numberParser = new RegexpParser("(\\d+)", 3, "three most commonly used numbers: "); 
    RegexpParser wordParser = new RegexpParser("([a-z]+)", 10, "ten most commonly used words: ");
    String filename;

    public CompleteParser(String filename) {
      this.filename = filename;
    }
    public void run() {
      ParserResult letterResult = letterParser.parse(filename);
      ParserResult charResult = charParser.parse(filename);
      ParserResult numberResult = numberParser.parse(filename);
      ParserResult wordResult = wordParser.parse(filename);

      letterResult.printOut();
      charResult.printOut();
      numberResult.printOut();
      wordResult.printOut();
    }
  }

  public static void main(String[] args) {
    try {
      LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
      ThreadPoolExecutor executor=new ThreadPoolExecutor(2,2,1000L,TimeUnit.SECONDS, queue, new RejectedExecutionHandler() { public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) { System.err.println("Err in running thread " + r); } });
      for (int i = 0; i < args.length; i++) {
        executor.submit(new CompleteParser(args[i]));
      }
      executor.shutdown();
      executor.awaitTermination(30, TimeUnit.SECONDS);
      executor.shutdownNow();
    }
    catch (InterruptedException ie) {
      System.err.println("Error wit threads..." + ie.getMessage());
    }
  }
}