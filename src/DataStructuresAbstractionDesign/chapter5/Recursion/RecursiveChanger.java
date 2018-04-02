package DataStructuresAbstractionDesign.chapter5.Recursion;

public class RecursiveChanger
{
    public static void main(String[] args)
    {
        int theValue = 99;

        // I looked this one up after multiple tries, credit programmer in Change.java
        // I did implement the wrapper for it which I do understand.
        System.out.println("All possible combination of " + theValue);
        printCombonationChange(theValue);
    }

    // wrapper
    public static void printCombonationChange(int change)
    {
        int coins[] = {25,10,5,1};
        int counts[] = new int[coins.length];
        printCombonationChange(coins, counts, 0, change);

    }


    // Credit to:  https://www.youtube.com/watch?v=3VBjhiKUtmE
    // Coins holds the value of each coin and must be sorted highest to lowest
    // counts records the number of coins at certain location
    // start index keeps track from which coin we start processing after choosing one larger coin amount
    // total amount keep track of remaining amount left proecessing
    public static void printCombonationChange(int[] coins, int[] counts, int startIndex, int totalAmount)
    {
        // Base Case
        if(startIndex >= coins.length)
        {
            // format the print as " amount = ?*25 + ?*10 +"
            System.out.print("" + totalAmount + "=");

            for(int i = 0; i < coins.length; i++)
            {
                System.out.print("" + counts[i] + "*" + coins[i] + "+");
            }
            System.out.println();
            return;
        }

        // otherwise proceed
        // if startIndex is the last one, we need to check if it can be dividable by the smallest coin
        // if so, this is a good combination, otherwise , this is not possible combination, discart
        if(startIndex == coins.length - 1)
        {
            if(totalAmount % coins[startIndex] == 0) // good combonation
            {
                // set the counts of coins at start index
                counts[startIndex] = totalAmount / coins[startIndex];

                // proceed to recursive call
                printCombonationChange(coins, counts, startIndex + 1, 0); //
            }
        }
        else // still have option to chose 0-N larger coins
        {
            for(int i = 0; i < totalAmount/ coins[startIndex]; i++)
            {
                // for every cycle in a loop, we choose an arbitrary number of larger coins
                counts[startIndex] = i ;

                // update remaining amount.
                printCombonationChange(coins, counts, startIndex + 1, totalAmount - coins[startIndex] * i);
            }
        }

    }


}
