public class CoinsApp{

    public static void main (String[] args){

        boolean[] b = {HEADS, TAILS, HEADS, HEADS, TAILS};
        Coins c = new Coins(b);
        System.out.println(c.countHeads());

    }

}
