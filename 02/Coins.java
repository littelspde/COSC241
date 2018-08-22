/** Coins class
    6/03/18
    @author Josh King **/

package week02;

import java.util.Random;

/** Coins class that creates arrays of coins
    and reports on its makeup.
@author Josh King**/
public class Coins{

    /** Setting heads as true. **/
    public static final boolean HEADS = true;
    /** Setting tails as false. **/
    public static final boolean TAILS = false;

    /** Creating an array to fill with coins. **/
    private boolean[] coins;
    /** Creating a counter for number of heads. **/
    public int numOfHeads;

    /** Constructor that takes an array of booleans.
        @param coins boolean array representing coins. **/
    public Coins(boolean[] coins) {
        this.coins = coins;
    }

    /** Constructor that takes a string of H's and T's.
        @param c a String representing coin faces */
    public Coins(String c){
        int stringLength = c.length();
        boolean[] coinsIn = new  boolean[stringLength];

        for (int x=0; x<stringLength; x++){
            if (c.charAt(x) == 'H'){
                coinsIn[x] = HEADS;
            } else if (c.charAt(x) == 'T'){
                coinsIn[x] = TAILS;
            }   
        }
        this.coins = coinsIn;
    }

    /** Constructer that randomises entered number of coins.
        @param length the number of coins to be randomly generated **/
    public Coins(int length){
        Random r = new Random();
        boolean[] coinsIn = new boolean[length];
        for (int x=0; x<length-1; x++){
            coinsIn[x] = r.nextBoolean();
        }
        this.coins = coinsIn;
        

    }

    /** Counts the number of heads in a set of coins.
        @return the number of heads in set **/
    public int countHeads(){
        numOfHeads = 0;
        for (boolean c:coins){
            if (c){
                numOfHeads++;
            }
        }
        return numOfHeads;

    }

    /** Returns a string displaying the faces of a set of coins.
        @return a string representation of the coin set **/
    public String toString(){
        String coinList = "";
        for (boolean c:coins){
            if (c){
                coinList += "H";
            } else{
                coinList += "T";
            }
        }
        return coinList;
    }

    /** Counts number of runs, consecutive groups of the same coin face.
        @return the number of runs in the coin set **/
    public int countRuns(){
        int numRuns = 0;
        boolean previous = false;
        if (!coins[0]){
            previous = true;
        }
            
        for (boolean c:coins){
            
            if (c != previous){
                numRuns++;
            }
            previous = c;
        }
        return numRuns;
    }
            

    /** Main Method. Creates coin arrays and runs methods on them.
        @param args the main script **/
    public static void main (String[] args){

        boolean[] b = {HEADS, TAILS, HEADS, HEADS, TAILS};
        Coins c = new Coins(b);
        Coins c2 = new Coins("HHTTHTH");
        System.out.println(c2.toString());
        System.out.println(c2.countHeads());
        System.out.println(c2.countRuns());

    }


}
