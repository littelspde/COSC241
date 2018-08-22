package week08;

import java.util.*;
import java.io.*;

/**
   A random word generator that uses the weighted frequencey of appearance
   of each letter after a specific letter.
   @author Josh King
*/
public class DigramGenerator implements WordGenerator {

    /** Variable to contain the random object passed in the constructor.*/
    private Random random;

    /** A string represetning weighted chance that a letter will be the
        first letter of a word. */
    String firstLetters;
    /** ArrayList to store the letters that may follow a certain letter.*/
    ArrayList<String> continuations = new ArrayList<String>();

    /** The file containing a string representing the weighted chance
        that a letter will appear as the first letter of a word. */
    File lettersIn = new File("first-letters.txt");
    /** The file containing series of strings representing the weighted
        frequency of what letter will appear after a given letter. */
    File continuationsIn = new File("continuations.txt");

    /** The scanner to read input from file. */
    Scanner inputFile;

    /**
       Constructer class for the digram random word generator.
       @param r the Random class object to be used in generation
    */
    public DigramGenerator(Random r) {
        random = r;
        try{
            inputFile = new Scanner(lettersIn);
            firstLetters = inputFile.next();
        } catch (FileNotFoundException fnf){
            System.out.println(fnf);
        }
        try{
            inputFile = new Scanner(continuationsIn);
        } catch (FileNotFoundException fnf){
            System.out.println(fnf);
        }
        while(inputFile.hasNextLine()){
            continuations.add(inputFile.nextLine());
        }
        
    }

    /**
       Generates a random word of given length.
       @param n desired length of generated word
       @return generated word
    */
    public String nextWord(int n) {
        StringBuilder result = new StringBuilder();
        int length = firstLetters.length();
        char previous = firstLetters.charAt(random.nextInt(length));
        result.append(previous);
        for (int i = 0; i < n-1; i++){
            int index = previous;
            index = index -'a';
            String possible = continuations.get(index);
            length = possible.length();
            char c = possible.charAt(random.nextInt(length));
            result.append(c);
            previous = c;
        }
        return result.toString();
    }

}
