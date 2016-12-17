
public class PrimalityTest {
    public static boolean isPrime(int n)
    {
        if (n == 1) return false;
        if (n == 2) return true;
        
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        int temp = (int) Math.sqrt(n);
        for (int i=5; i <= temp; i += 6)
        {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        
        return true;
    }
    
    public static void main(String[] args)
    {
        System.out.println(isPrime(100000007));
    }
}
