package hofs;

import java.lang.String;
import java.lang.StringBuilder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class represent a Sentence. 
 * It contains all operations that a sentence should
 * support.
 */
public class Sentence {
  private LinkedList<String> sentenceList;
  private int listSize;
  
  /**
   * Constructs a empty sentence with no parameters.
   */
  public Sentence() {
    this.sentenceList = new LinkedList<String>();
    this.listSize = 0;
  }
  
  /**
   * Constructor of a sentence from a given string. 
   * By breaking down the input string into the individual 
   * words and punctuation marks found in the input.
   * @param input the given string input with words and punctuation.
   */
  public Sentence(String input) {
    this.sentenceList = new LinkedList<String>();
    
    /* convert the string into an array list of string using HOF */
    List<String> listOfInput = Stream.of(input.split(" "))
                              .map(elem -> new String(elem))
                              .collect(Collectors.toList());
    
    /* convert array list to linked list*/
    for (String s : listOfInput) {
      if (!s.contains(" ")) {
        this.sentenceList.add(s);
      }
    }
    
    LinkedList<String> modifiedList = new LinkedList<String>();
    
    Iterator<String> itr = this.sentenceList.iterator();  
    
    while (itr.hasNext()) {
      String curr = itr.next();
      if (containsNumberOfPunc(curr) != 0) {
        StringBuilder newCurr = new StringBuilder(curr);
        for (int i = 0; i < curr.length(); i++) {
          if (isPunctuation(curr.charAt(i))) {
            newCurr.deleteCharAt(i);
          }
        }
        String punMark = splitPuncWithWord(curr);
        curr = newCurr.toString();
        modifiedList.add(curr);
        modifiedList.add(punMark);
      } else {
        modifiedList.add(curr);
      }
    }
    this.sentenceList = modifiedList;
    /* update the size of the sentence */
    this.listSize = this.sentenceList.size();
  }
  
  /**
   * Constructor of a duplicate of the other sentence. 
   * This duplicate should have the same words and punctuation in the 
   * same order but should be independent of the provided sentence.
   * @param other the other sentence that need to be duplicated.
   */
  public Sentence(Sentence other) {
    this.sentenceList = new LinkedList<String>();
    this.listSize = other.getListSize();
    
    /* Using iterator to copy every node from other to this */
    Iterator<String> itr = other.getList().iterator();  
    while (itr.hasNext()) {
      this.sentenceList.add(itr.next());
    }
  }
  
  /**
   * This is a helper function to know whether a character.
   * is a punctuation or not. 
   * @param c the input character.
   * @return true of false the character is a punctuation.
   */
  private boolean isPunctuation(char c) {
    if ((c - '!' >= 0 && c - '!' <= 14) 
        || (c - ':' >= 0 && c - ':' <= 6)) {
      return true;
    }
    return false;
  }

  /**
   * This is a helper function to split the punctuation with a word.
   * and return the punctuation as a separate string.
   * @param str the input string.
   * @return string with only the punctuation.
   */
  private String splitPuncWithWord(String str) {
    String output = "";
    if (str.length() <= 0) {
      return output;
    }
    for (int i = 0; i < str.length(); i++) {
      if (isPunctuation(str.charAt(i))) {
        output += str.charAt(i);
      }
    }
    return output;
  }
  
  /**
   * This is a helper function count how many punctuation.
   * and return the number of punctuation in the string.
   * @param str the input string.
   * @return the number of punctuation in the given string
   */
  private int containsNumberOfPunc(String str) {
    int countPunctuations = 0;
    if (str.length() <= 0) {
      return countPunctuations;
    }
    for (int i = 0; i < str.length(); i++) {
      //this char is not a letter
      if (isPunctuation(str.charAt(i))) {
        countPunctuations++;
      }
    }
    return countPunctuations;
  }
  
  /**
   * This is a helper function for pit latin translation.
   * and return the character is a vowel or not.
   * @param c the input character.
   * @return true or false the character is a vowel
   */
  private boolean isVowel(char c) {
    return (c == 'A' || c == 'E' 
        || c == 'I' || c == 'O' 
        || c == 'U' || c == 'a' 
        || c == 'e' || c == 'i' 
        || c == 'o' || c == 'u');
  }
  
  /**
   * Get the size of the sentence.
   * @return the size
   */
  public int getListSize() {
    return this.listSize;
  }
  
  /**
   * Get the lined List of the sentence.
   * @return the list
   */
  public LinkedList<String> getList() {
    return this.sentenceList;
  }
  
  /**
   * This method takes a single word or punctuation mark.
   * and adds it to the end of the sentence. 
   * if the argument contains more than one word, a word with punctuation, 
   * or multiple punctuation marks, throw IllegalArgumentException
   * @param word the given string input of words or punctuation.
   */
  public void add(String word) throws IllegalArgumentException {
    int numberOfLetter = 0;
    int numberOfPunc = 0;
    for (int i = 0; i < word.length(); i++) {
      if (isPunctuation(word.charAt(i))) {
        numberOfPunc++;
      } else {
        numberOfLetter++;
      }
    }
    /* contains more than one punctuation, throw exception. */
    if (numberOfPunc > 1) {
      throw new IllegalArgumentException("Must contain no more than one punctuation");
    }
    /* contains word with punctuation, throw exception. */
    if (numberOfLetter != 0 && numberOfPunc != 0) {
      throw new IllegalArgumentException("Must contain only punctuation or word");
    }
    /* contains more than one word, throw exception. */
    if (word.contains(" ")) {
      throw new IllegalArgumentException("Must contain only one word");
    }
    
    this.sentenceList.add(word);
    this.listSize++;
  }

  /**
   * This method computes and returns the number of punctuation in a given sentence.
   * @return number of punctuation in the sentence.
   */
  public long getNumberOfMarks() {
    long numberOfPunc = 0;
    Iterator<String> itr = this.sentenceList.iterator();  
    while (itr.hasNext()) {
      String curr = itr.next();
      if (containsNumberOfPunc(curr) != 0) {
        numberOfPunc++;
      }
    }
    return numberOfPunc;
  }
  
  /**
   * This method computes and returns the number of words in a sentence.
   * @return number of words in the sentence.
   */
  public long getNumberOfWords() {
    return this.listSize - this.getNumberOfMarks();
  }

  /**
   * This method computes and returns the words in the list.
   * that have the specified substring in them.
   * @param substring the specific substring to find
   * @return number of words in the sentence that contains this letter.
   */
  public long countWordsWith(String substring) {
    long answer = this.sentenceList
                      .stream()
                      .filter(str -> str.contains(substring))
                      .count();
    return answer;
  }
  
  /**
   * This method determines and returns the longest word in a sentence.
   * The word returned is just the word. If the sentence contains no words,
   * this method should return an empty string.
   * @return the longest word in the list.
   */
  public String longestWord() {
    String output = "";
    int maxLength = this.sentenceList.stream()
                        .map(str -> str.length())
                        .max(Integer::compare)
                        .get();
    Iterator<String> itr = this.sentenceList.iterator();  
    while (itr.hasNext()) {
      String curr = itr.next();
      if (curr.length() == maxLength) {
        /* determine if this is a pure world. */
        if (containsNumberOfPunc(curr) == 0) {
          return curr;
        }
      }
    }
    return output;
  }
  
  /**
   * This method will merge two sentences into a single sentence.
   * @param other the sentence that will be merged with this sentence
   * @return the merged sentence
   */
  public Sentence merge(Sentence other) {
    Sentence mergedSentence = new Sentence();
    
    /* add this sentence to the merged sentence. */
    Iterator<String> itr = this.sentenceList.iterator();  
    while (itr.hasNext()) {
      String curr = itr.next();
      mergedSentence.sentenceList.add(curr);
    } 
    
    Iterator<String> itrOther = other.sentenceList.iterator();  
    while (itrOther.hasNext()) {
      String curr = itrOther.next();
      mergedSentence.sentenceList.add(curr);
    } 
    
    mergedSentence.listSize = other.getListSize() + this.getListSize();
    return mergedSentence;
  }
  
  /**
   * This is the helper function for the PigLatin method.
   * @param str the string that will be converted to pig latin.
   * @return the new string after conversion
   */
  public String helperPigLatin(String str) {
    /* the index of the first vowel is stored. */
    int len = str.length();
    int index = -1;
    for (int i = 0; i < len; i++) {
      if (isVowel(str.charAt(i))) {
        index = i;
        break;
      }
      if (str.charAt(i) == 'y' && i > 1) {
        index = i;
        break;
      }
    }
    
    /* Take all characters after index (including
     * index). Append all characters which are before
     * index. Finally append "ay"
     */
    if (len > 1) {
      if (str.charAt(1) == 'y') {
        index = 1;
      }
    }
    if (index == 0) {
      return str.substring(index) + "yay";
    } else {
      return str.substring(index) 
            + str.substring(0, index) 
            + "ay";
    }
  }
  
  /**
   * returns a new sentence where all of the words. 
   * in the sentence have been encoded into Pig Latin 
   * using all lower case. Numbers and punctuation do not change. 
   * @return the modified pig latin sentence
   */
  public Sentence toPigLatin() {
    Sentence pigLatinSentence = new Sentence();
    
    Iterator<String> itr = this.sentenceList.iterator(); 
    while (itr.hasNext()) {
      String curr = itr.next();
      curr = curr.toLowerCase();
      /* if this word is a punctuation, 
       * we don't need to change it. 
       */
      if (containsNumberOfPunc(curr) != 0) {
        pigLatinSentence.sentenceList.add(curr);
      } else {
        pigLatinSentence.sentenceList.add(helperPigLatin(curr));
      }
    }
    return pigLatinSentence;
  }
  
  /**
   * convert the sentence into one string.
   * @return the string format of the sentence.
   */
  public String toString() {
    String output = "";
    Iterator<String> itr = this.sentenceList.iterator(); 
    while (itr.hasNext()) {
      String curr = itr.next();
      /* if this word is a punctuation, 
       * we don't need to add space.
       */
      if (containsNumberOfPunc(curr) != 0) {
        output += curr;
      } else {
        output = output + " " + curr;
      }
    }
    
    /* check if the last char of the sentence is a punctuation
     * or not. if no punctuation, add a "."
     */
    if (output.length() > 0) {
      if (!isPunctuation(output.charAt(output.length() - 1))) {
        output = output + ".";
      }
      /* remove the first space or last space of the string */
      if (Character.isWhitespace(output.charAt(0)) 
          || Character.isWhitespace(output.charAt(output.length() - 1))) {
        output = output.trim();
      }
    }
    
    return output;
  }
}