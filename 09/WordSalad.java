/* File: WordSalad.java - April 2018 */
package week09;
import java.util.*;

/**
 *  Implementation of the WordSalad class.
 *
 *  @author Michael Albert
 @author Josh King
 @author Darcy Knox
 @author Surayo Akramkhanova
*/
public class WordSalad implements Iterable<String> {

    /** data field to refer to the first node.*/
    private WordNode first;
    /** data field to refer to the last node.*/
    private WordNode last;

    /**
       Constructor for WordSalad object.
    */
    public WordSalad() {
        this.first = null;
        this.last = null;
    }

    /**
       Constructor for WordSalad object.
       @param words List of Strings to be added to the WordSalad object.
    */
    public WordSalad(java.util.List<String> words) {
        for (String word : words) {
            addLast(word);
        }
    }

    /**
       Adds a word to the beginning of a WordSalad object.
       @param word Word to be added to WordSalad.
    */
    public void add(String word) {
        if (this.first == null) {
            this.first = new WordNode(word, null);
            this.last = this.first;
            return;
        }
        WordNode newFirst = new WordNode(word, this.first);
        this.first = newFirst;
    }

    /**
       Adds a word to the end of a WordSalad object.
       @param word Word to be added to WordSalad.
    */
    public void addLast(String word) {
        if (this.first == null) {
            add(word);
            return;
        }
        WordNode newLast = new WordNode(word, null);
        this.last.next = newLast;
        this.last = newLast; 
    }

    /**
       Inner class representing a singly-linked node within a WordSalad.
    */
    private class WordNode {

        /** Inner class data field to store the word.*/
        private String word;
        /** Inner class data field to refer to the next node.*/
        private WordNode next;

        /**
           Constructor for WordNode object.
           @param word The word to be stored in the WordNode.
           @param next The WordNode that this node will contain a link to.
        */
        private WordNode(String word, WordNode next) {
            this.word = word;
            this.next = next;
        }
        
    }

    /** Method that sets up the behaviour of the Iterator interface.
        Creates an anonymous implementation of Iterator<String>
        @return java.util.Iterator<String>, a new iterator object
        for each call of iterator() method
    */
    public java.util.Iterator<String> iterator() {
        return new java.util.Iterator<String>() {
            private WordNode current = first;

            /**
               Returns true if the iteration has more elements.
            */
            public boolean hasNext() {
                return current != null;
            }

            /**
               Returns the next object from the iteration.
            */
            public String next() {
                String result = current.word;
                current = current.next;
                return result;
            }

            /**
               Not implemented so throws an exception.
            */
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
       Converts a WordSalad object into a String for printing.
       @return The contents of this WordSalad object as a string.
    */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        WordNode node = first;
        while (node != null) {
            result.append(node.word);
            result.append(node.next == null ? "" : ", ");
            node = node.next;
        }
        return result.toString() + "]";
    }


    // Method stubs to be completed for the assignment.
    // See the assignment description for specification of their behaviour.

    /**
       Distributes the contents of a WordSalad object into k separate
       WordSalad objects stored in an array. Stores each word in a subsequent
       WordSalad object until it reaches the end of the array
       and loops through again.
       @param k The number of separate WordSalad objects to be distributed
       amongst.
       @return An array of WordSalad objects containing the distributed words.
    */
    public WordSalad[] distribute(int k) {

        WordSalad[] result = new WordSalad[k];
        int count = 0;
        for (int i = 0; i < k; i++){
            result[i] = new WordSalad();
        }

        for (String word: this){
            result[count].addLast(word);
            count ++;
            if (count > k-1){
                count = 0;
            }

        }
        return result;
    }

    /** Divides WordSalad objects into k equal (or nearly equal) pieces.
     * @param k number of blocks/divisions.
     * @return result an array of WordSalad objects.
     */

    public WordSalad[] chop(int k) {

        WordSalad[] result = new WordSalad[k];

        for (int i = 0; i < k; i++){
            result[i] = new WordSalad();
        }
        int words = 0;
        for (String word: this){
            words++;
        }

        int rem = words % k;
        int wordsIn = words/k;
        int count = 0;
        int index = 0;
        for (String word: this){
            if (rem > 0){
                result[index].addLast(word);
                count++;
                if (count >= wordsIn + 1){
                    count = 0;
                    rem--;
                    index++;
                }
            } else {
                result[index].addLast(word);
                count++;
                if (count >= wordsIn){
                    count = 0;
                    index++;
                }
            }
        }
        return result;
    }

    /**Splits the contents of a WordSalad object into increasingly
       smaller WordSalads.
       * Takes every kth word in a WordSalad and stores them together
       in a new WordSalad,
       * then takes the remaining unstored words and takes every kth word and
       * stores together in a WordSalad.
       * Repeats until all words have be stored in new WordSalads.
       * @param k The value by which the WordSalad should be split.
       * @return An array WordSalad objects containing the split words.
       */

    public WordSalad[] split(int k) {
        int count = 0;
        WordSalad[] result = new WordSalad[1];
        WordSalad temp = new WordSalad();

        result[0] = new WordSalad();

        for (String word: this){
            if (count == 0){
                result[0].addLast(word);
            } else {
                temp.addLast(word);
            }
            count++;
            if (count > k-1){
                count = 0;
            }
        }
        if (temp.first != null){
            result = combine(result, temp.split(k));
        }
        return result;
    }

    /**
     * Takes two arrays and makes a new array with the
     elements of array a followed
     * by the elements of array b.
     * @param a The array to be placed at the beginning of the combined array
     * @param b The array to be placed behind array a in the combined array
     @return an array of type WordSalad.
    */

    private static WordSalad[] combine (WordSalad[] a, WordSalad[] b){

        int length = a.length + b.length;
        WordSalad[] result = new WordSalad[length];

        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


    /** Merges an array WordSalad objects created by the distribute method
     * back into a single WordSalad containing the original order of words.
     * @param blocks An array of WordSalad objects created by the
     distribute method
     * @return A WordSalad object containing the distributed
     words in their original order
    */

    public static WordSalad merge(WordSalad[] blocks) {

        int count = 0;
        int target = 0;

        WordSalad result = new WordSalad();
        
        for (int i = 0; i <= blocks.length; i++){
            for (WordSalad wS: blocks){
                for (String word: wS){
                    if (count == target){
                        result.addLast(word);
                    }
                    count++;
                }
                count = 0;
            }
            target++;
        }
        return result;
    }


    /** Joins the array of WordSalad objects created by the chop method
        into a single WordSalad object.
        @param blocks - an array of WordSalad objects created by chop method
        @return WordSalad object containing chopped words in the original order
    */
    public static WordSalad join(WordSalad[] blocks) {

        WordSalad result = new WordSalad();

        for (WordSalad wordSalad: blocks){
            for (String word: wordSalad){
                result.addLast(word);
            }
        }
        

        
                
        return result;
    }

    /** Recombines an array of WordSalad Objects created by the split method
     *  into a single WordSalad object.
     *  @param blocks An array of WordSalad objects to be recombined
     *  @param k The value by which the origianl WordSalad object was spilt
     *  @return A WordSalad object containing the spilt words in original order
     */

    public static WordSalad recombine(WordSalad[] blocks, int k) {
        ArrayList<String> resultArList = new ArrayList<String>();

        WordSalad[] blocksReverse = new WordSalad[blocks.length];
        WordSalad result = new WordSalad();
        
        int index = 0;

        for (int count = blocks.length-1; count >= 0; count--){
            blocksReverse[index] = blocks[count];
            index++;
        }

        for(WordSalad wS : blocks){
            if(wS.first.next == null){
                resultArList.add(wS.first.word);
            }
        }
        
        for (WordSalad wS : blocksReverse){
            if(wS.first.next != null){
                index = 0;
                for(String word: wS){
                    resultArList.add(index, word);
                    index += k;
                }
            }
        }

        for (String word : resultArList){
            result.addLast(word);
        }

        return result;
    }

}
