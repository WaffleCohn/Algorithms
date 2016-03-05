
import java.math.BigInteger;



public class Fibonacci {
    
    // NOTE: use BigInt for some problems
    
    /**
     * Returns Fibonacci value at index n
     * @param n the index to get the value at
     * @return the Fibonacci value
     */
    public static int fib(int n)
    {
        if (n < 1)
            return -1;
        if (n < 3)
            return 1;
        
        int previous = 1;
        int penultimate = 1;
        int current = 0;
        
        for (int i=2; i < n; i++)
        {
            current = previous + penultimate;
            penultimate = previous;
            previous = current;
        }
        
        return current;
    }
    
    public static BigInteger fib2(int n)
    {
        if (n < 1)
            return new BigInteger("-1");
        if (n < 3)
            return new BigInteger("1");
        
        BigInteger previous = new BigInteger("1");
        BigInteger penultimate = new BigInteger("1");
        BigInteger current = new BigInteger("0");
        
        for (int i=2; i < n; i++)
        {
            current = previous.add(penultimate);
            penultimate = previous;
            previous = current;
        }
        
        return current;
    }
    
    public static void main(String[] args)
    {
        for (int i=1; i <= 10; i++)
        {
            System.out.println(fib(i));
        }
        
        System.out.println(fib2(10));
    }
    
}
