package week03;

/**
   A recursive app that gives the the number of digits or sum of digits
   in a given long.
   
   @author Josh King
*/

public class RecursiveApp{

    /**
       Returns the number of digits in a given long.
       @param n long input to be digit counted
       @return a long representing the number of digits in input number
     */
    public static long digits(long n){
        if(n < 10 && n > 0){
            return 1;
        } else if (n > 10 || n < 0 ){
            return 1 + digits(n/10);
        }
        return 0;
    }

    /**
       Sums the digits of a given long.
       @param n long input to be summed
       @return a long representing the sum of the digits of the input
    */
    public static long sumOfDigits(long n){
        if (n != 0){
            long sum = n % 10;
            return sum + sumOfDigits(n/10);
        }
        return 0;
    }
}
