package week12;

/**
 *  Practical test 2 - Part A
 *
 *  An array based implementation of Young's tableau.
 *
 * @author Iain Hewson
 */
public class TableauApp {

    /**
     * The main method is just used for testing.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        final int[][] valid = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
        System.out.println(TableauApp.toString(valid));
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        return rowLengthsDecrease(t) && rowValuesIncrease(t) &&
            columnValuesIncrease(t) && isSetOf1toN(t);
    }

    /**
     *  Returns a string representation of a tableau.
     *
     * @param t a two-dimensional array which should be a tableau.
     *
     * @return a string representation of a tableau.
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
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean rowLengthsDecrease(int[][] t) {
        int pLen;

        if((t.length == 0) ||(t.length == 1 && t[0].length == 1)){
            return true;
        } else{
            pLen = t[0].length;
        }
        
        for(int[] row : t){
            if (row.length > pLen){
                return false;
            }
            pLen = row.length;
        }
        return true;
    }    

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean rowValuesIncrease(int[][] t) {
        int pVal;

        if((t.length ==0) ||(t.length == 1 && t[0].length == 1)){
            return true;
        }else{
            pVal = t[0][0] -1;
        }

        for(int[] row : t){
            for(int value : row){
                if (value < pVal){
                    return false;
                }
                pVal = value;
            }
            pVal = row[0];
        }
        return true;
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean columnValuesIncrease(int[][] t) {

        for(int r=0; r < t.length; r++){
            for(int c =0; c < t[r].length; c++){
                if (r >0 && c < t[r-1].length){
                    if(t[r][c] < t[r-1][c]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Determines whether the array passed to it is valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isSetOf1toN(int[][] t) {

        int tLen = 0;
        for(int[] row : t){
            tLen += row.length;
        }

        int[] aVal = new int[tLen];
        int count = 0;
        for(int[]row : t){
            for(int value: row){
                aVal[count] = value;
                count++;
            }
        }

        for(int i=0; i <tLen; i++){
            for(int j =0; j<tLen; j++){
                if(aVal[i] < aVal[j]){
                    int temp = aVal[i];
                    aVal[i] = aVal[j];
                    aVal[j] = temp;
                }
            }
        }

        int current = 1;
        for(int value : aVal){
            if (value != current){
                return false;
            }
            current++;
        }


        return true;
    }
}
