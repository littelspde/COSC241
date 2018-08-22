/** @author  Josh King
   Java Line and Word Counter
   27/02/18 */

package week01;

import java.util.Scanner;

/** Checks input and counts lines and words.
 @author Josh King */ 
public class Counter{

    /** Checks input and counts lines and words.
     * @param  args */ 
    public static void main (String[] args){

        Scanner lineSc = new Scanner(System.in);
        int lines = 0, words = 0;

        /** Checks for next line, adds 1 to line then scans line for words*/
        while (lineSc.hasNextLine()){
            String lineString =  lineSc.nextLine();
            lines += 1;
            Scanner wordSc = new Scanner(lineString);
            while (wordSc.hasNext()){
                words += 1;
                wordSc.next();
            }
        }

      
        /** Prints out number of lines and words in input */
        System.out.println("lines: " + lines);
        System.out.println("words: " + words);

 

    }
}
