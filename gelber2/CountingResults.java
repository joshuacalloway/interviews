/*
 * Joshua Calloway
 * GelberGroup programming exercise
 * 08/24/2011
 */

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


// templated class count either characters, digits, or strings
public class CountingResults<ClassOfWhatsCounted> {
  Map<ClassOfWhatsCounted, Integer> countMap = new HashMap<ClassOfWhatsCounted, Integer>();
  String what;
  int mostCommonLimit=1;  // for printing 3 most common or 5 most common
  Filter filter;

  interface Filter<FilterType> {
    boolean isCounted(Character c);
    FilterType getItemToCount();
  }

  public void setFilter(Filter filter) {
    this.filter = filter;
  }
  
  // what is used for labeling what's counted
  public CountingResults(String what) {
    this.what = what;
  }
     
  public String getWhat() {
    return what;
  }

  public void setMostCommonLimit(int val) {
    mostCommonLimit=val;
  }

  public CountingResults<ClassOfWhatsCounted> mergeResults(final CountingResults<ClassOfWhatsCounted> toMerge) {
    for (Map.Entry<ClassOfWhatsCounted, Integer> entry : toMerge.countMap.entrySet()) {
      ClassOfWhatsCounted key = entry.getKey();
      Integer value = entry.getValue();
      if (countMap.containsKey(key)) {
	countMap.put(key, countMap.get(key) + value);
      } else {
	countMap.put(key, value);
      }
    }
    return this;
  }

  public void count(char ch) {
    if (!filter.isCounted(ch)) {
      return;
    }
    ClassOfWhatsCounted item = (ClassOfWhatsCounted)filter.getItemToCount();
    if (countMap.containsKey(item)) {
      countMap.put(item, countMap.get(item) + 1 );
    } else {
      countMap.put(item, 1);
    }      
  }

  private LinkedHashMap<ClassOfWhatsCounted, Integer> sortMapBasedOnValues() {
    List<ClassOfWhatsCounted> mapKeys = new ArrayList<ClassOfWhatsCounted>(countMap.keySet());
    List<Integer> mapValues = new ArrayList<Integer>(countMap.values());
    Collections.sort(mapValues, new Comparator<Integer>() {
	@Override public int compare(Integer a, Integer b) {
	  return b.compareTo(a);   // reverse sort
	}
      });
    LinkedHashMap<ClassOfWhatsCounted, Integer> sortedMap = new LinkedHashMap<ClassOfWhatsCounted, Integer>();
    Iterator<Integer> valueIt = mapValues.iterator();
    while (valueIt.hasNext()) {
      Integer val = valueIt.next();
      Iterator<ClassOfWhatsCounted> keyIt = mapKeys.iterator();
      while (keyIt.hasNext()) {
	ClassOfWhatsCounted key = keyIt.next();
	Integer comp1 = countMap.get(key);
	Integer comp2 = val;
          
	if (comp1.equals(comp2)){
	  countMap.remove(key);
	  mapKeys.remove(key);
	  sortedMap.put((ClassOfWhatsCounted)key, (Integer)val);
	  break;
	}
	  
      }
    }
    return sortedMap;
  }
    
  public void printMostCommon() {
    LinkedHashMap<ClassOfWhatsCounted, Integer> sorted = sortMapBasedOnValues();
    int i = 0;
    System.out.println("Printing " + mostCommonLimit + " most common occurrences of " + what +".");
    for (Map.Entry<ClassOfWhatsCounted, Integer> entry : sorted.entrySet()) {
      ClassOfWhatsCounted key = entry.getKey();
      Integer value = entry.getValue();
      System.out.println(key + " occurred " + value + " times.");
      ++i;
      if ( i >= mostCommonLimit) {
	break;
      }
    }
  }

  static class LetterCountingResults extends CountingResults<Character> {
    public LetterCountingResults() {
      super("letters");
      setFilter(new CountingResults.Filter<Character>() {
	  Character item;
	  public boolean isCounted(Character ch) {
	    boolean isLetter = Character.isLetter(ch);
	    if (isLetter) {
	      item = ch;
	    }
	    return isLetter;
	  }
	  public Character getItemToCount() {
	    return item;
	  }
	});
    }
  }

  static class NumberCountingResults extends CountingResults<Integer> {

    public NumberCountingResults() {
      super("numbers");
      setFilter(new CountingResults.Filter<Integer>() {
	  String currentDigit="";
	  Integer item;
	  public boolean isCounted(Character ch) {
	    if (Character.isDigit(ch)) {
	      currentDigit += ch;
	    } else if (!currentDigit.equals("")) {
	      item = Integer.parseInt(currentDigit);
	      currentDigit = "";
	      return true;
	    }
	    return false;
	  }
	  public Integer getItemToCount() {
	    return item;
	  }
	});
    }
  }
  static class WordCountingResults extends CountingResults<String> {
    public WordCountingResults() {
      super("words");
      setFilter(new CountingResults.Filter<String>() {
	  String currentWord="";
	  String item;
	  public boolean isCounted(Character ch) {
	    if (Character.isLetter(ch)) {
	      currentWord += ch;
	    } else if (!currentWord.equals("")) {
	      item = currentWord;
	      currentWord = "";
	      return true;
	    }
	    return false;
	  }
	  public String getItemToCount() {
	    return item;
	  }
	});
    }
  }
  
  static class CharacterCountingResults extends CountingResults<Character> {
    public CharacterCountingResults() {
      super("characters");
      setFilter(new CountingResults.Filter<Character>() {
	  Character item;	  
	  public boolean isCounted(Character ch) {
	    item = ch;
	    return true;   // everything including whitespace is a character
	  }
	  public Character getItemToCount() {
	    return item;
	  }
	});
    }
  }
}
