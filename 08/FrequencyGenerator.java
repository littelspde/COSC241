package week08;

import java.util.*;
import java.io.*;

/**
   A random word generator that uses the weighted frequencey of appearance
   of each letter.
   @author Josh King
*/

public class FrequencyGenerator implements WordGenerator {

    /** Variable to contain the random object passed in the constructor.*/
    private Random random;

    /** ArrayList to store the weights read from file.*/
    ArrayList<Double> weights = new ArrayList<Double>();

    /** The file containing the weighted frequency of letter use.*/
    File fileIn = new File("letter-frequencies.txt");
    
    /** The scanner to read input from file. */
    Scanner inputFile;

    /**
       Constructer class for the frequency random word generator.
       @param r the Random class object to be used in generation
    */
    public FrequencyGenerator(Random r) {
        random = r;
        try{
            inputFile = new Scanner(fileIn);
        } catch (FileNotFoundException fnf){
            System.out.println(fnf);
        }
        while(inputFile.hasNext()){
            weights.add(inputFile.nextDouble());
        }

    }

    /**
       Generates a weighted int that can be used to randomise characters
       in a weighted way.
       @param w the array list of weights to select from
       @return a weighted int used for character selection
    */
    public int chooseIndex(ArrayList<Double> w){
        int count = 0;
        Double  r = random.nextDouble();
        while (r > w.get(count)){
            r = r - w.get(count);
            count++;
        }
        return count;
    }

    /**
       Generates a random word of given length.
       @param n desired length of generated word
       @return generated word
    */

    public String nextWord(int n){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <n; i++){
            char c = (char) ('a' + chooseIndex(weights));
            result.append(c);
        }
        
        return result.toString();
    }

}
