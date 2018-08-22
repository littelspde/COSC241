package week04;

/**
 * Skeleton code for an array based implementation of Young's tableau.
 *
 * @author Iain Hewson
 * @author Josh King
 */
public class TableauApp {

    /**
     * The main method is just used for testing.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        
        final int[][] valid = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
        
    }

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){

        if(rowLengthsDecrease(t) && rowValuesIncrease(t) &&
            columnValuesIncrease(t) && isSetOf1toN(t)){
            return true;
        }
        return false;
    }

    /**
     *  Returns a string representation of an array based tableau.
     *
     * @param t a two-dimensional array which represents a tableau.
     *
     * @return a string representation of an array based tableau.
     */
    public static String toString(int[][] t) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                result.append(String.format("%-4s", t[i][j]));
            }
            if (i < t.length-1) {
                result.append("\n");
            }
        }
        return result.toString();
    }


    /**
       Determines whether or not the length of each row descreases
       in a given 2D array.
       @param t a 2D array to be checked
       @return a boolean representing if the rows of 2D array decrease in length
     */
    public static boolean rowLengthsDecrease(int[][] t){
        boolean decrease = false;
        int previousLength;

        if ((t.length == 0) || (t.length == 1 && t[0].length == 1)){
            return true;
        }else{
            previousLength = t[0].length;
        }
        
        for (int[] row : t){
            if (row.length <= previousLength){
                decrease = true;
            }else{
                return false;
            }
            previousLength = row.length;
        }
        return decrease;
    }

    /**
       Determines whether the value increase along the rows of a 2D array.
       @param t a 2D array to be checked
       @return a boolean representing if the values in each row
       of a 2D array increase 
     */
    public static boolean rowValuesIncrease(int[][] t){
        boolean increase = false;
        int previousValue;
        
        if ((t.length == 0) || (t.length == 1 && t[0].length == 1)){
            return true;
        }else{
            previousValue = t[0][0] - 1;
        }
            

        for (int[] row : t){
            for (int value : row){
                if (value > previousValue){
                    increase = true;
                }else{
                    return false;
                }
                previousValue = value;
            }
            previousValue = row[0] - 1;
        }
        return increase;
    }

    /**
       Determines whether the value increases down the columns of a
       given 2D array.
       @param t a 2D array to be checked
       @return a boolean representing if the values in each column increase
    */
    public static boolean columnValuesIncrease(int[][] t){

        for (int r=0; r < t.length; r++){
            for (int c = 0; c < t[r].length; c++){
                if(r > 0 && c < t[r-1].length){
                    if(t[r][c] < t[r-1][c]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
       Checks if a given 2D array contains the number set {1,2...N}
       with N being the total length of all the 1D arrays.
       @param t the 2D array to be checked
       @return a boolean representing if the 2D array contains the
       full set of numbers
    */
    public static boolean isSetOf1toN(int[][] t){

        // establishes the total length of the 2D array (N)
        int totalLength = 0;
        for (int[] row : t){
            totalLength += row.length;
        }

        //puts all values from 2D array into one array
        int[] allValues = new int[totalLength];
        int count = 0;
        for (int[] row : t){
            for (int value : row){
                allValues[count] = value;
                count++;
            }
        }

        //sorts the array
        for (int i = 0; i < allValues.length - 1; i++){
            for( int j = i + 1; j < allValues.length; j++){
                if (allValues[i] > allValues[j]){
                    int prev = 0;
                    prev = allValues[j];
                    allValues[j] = allValues[i];
                    allValues[i] = prev;
                }
            }
        }

        //check to see if array contains set 1,2... N
        int current = 1;
        for (int value : allValues){
            if (value == current){
                current++;
            }else{
                return false;
            }
        }
        return true;
        
    }
}
