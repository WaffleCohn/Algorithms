
public class SieveOfEratosthenes {
    
    /**
     * Finds all of the prime numbers from 1..N
     * @param N how many numbers to check for primality
     */
    public static void sieve(int N)
    {
        boolean[] nbrs = new boolean[N+1];
        int i, j;
        for (i=2; i <= N; i++)
            nbrs[i] = true;
        
        for (i=2; i <= N/2; i++)
            for (j=2; j <= N/i; j++)
                nbrs[i*j] = false;
        
        for (i=1; i <= N; i++)
            if (nbrs[i]) System.out.println(i);
    }
    
    public static void main(String[] args)
    {
        sieve(10); // prints 2, 3, 5, 7
    }
    
}
