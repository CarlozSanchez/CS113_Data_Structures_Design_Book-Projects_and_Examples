package Chapter5.Recursion;

public class programming1
{
    public static void main(String[] args)
    {
        int num;

        num = 10;
       // System.out.println("The mystery is" + mystery(4));
        //System.out.println("Factorial of " + num + " equals: " + factorial(num));
        System.out.println("The fibonacci of " + num + " equals: " + fibonacciStart(num));
    }

    /**
     * WRAPPER: calls recursiveSum(String)
     */
    public static void callResruvieSum()
    {
        int x, n;
        x = 2;
        n = 10;

        System.out.print("The power of " + x + "^" + n + "equals: ");
        System.out.println(recursivePower(x, n));
    }

    /**
     * Used to calculate the sum of values in a string.
     * @param str
     * @return
     */
    public static int recursiveSum(String str)
    {
        if (str.length() == 0)
        {
            return 0;
        }
        else if (Character.isDigit(str.charAt(0)))
        {
            return Character.digit(str.charAt(0), 10) + recursiveSum(str.substring(1, str.length()));
        }
        else
        {
            return 0 + recursiveSum(str.substring(1, str.length()));
        }
    }

    /**
     * used to calculate the value of a number to a power ex: 2^8
     * @param x
     * @param n
     * @return
     */
    public static int recursivePower(int x, int n)
    {
        if (n == 0)
        {
            return 1;
        }
        else
        {
            return x * recursivePower(x, n - 1);
        }
    }

    public static int mystery(int  num)
    {
        if(num ==0)
        {
            return 0;
        }
        else
        {
            return num * num + mystery(num -1);
        }
    }

    public static int factorial(int n)
    {
        if(n == 0)
        {
            return 1;
        }
        else
        {
            return n * factorial(n-1);
        }
    }

    public static int fibonacci(int n)
    {
        if (n <= 2)
        {
            return 1;
        }
        else
        {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public static int fibonacciStart(int n)
    {
        return (fibonacciOn(1,0,n));
    }

    public static int fibonacciOn(int fibCurrent, int fibPrevious, int n)
    {
        if (n == 1)
        {
            return fibCurrent;
        }
        else
        {
            return fibonacciOn(fibCurrent + fibPrevious, fibCurrent,n-1);
        }
    }

}
