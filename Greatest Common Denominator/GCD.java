
public class GCD {
    
    /**
     * Returns the greatest common denominator between two integers
     * @param a positive integer
     * @param b positive integer
     * @return the greatest common denominator
     */
    public static int gcd(int a, int b) {
	if (a == 0)
            return b;

	while (a != 0) 
        {
            int temp = a;
            a = b - a;
            b = temp;
            
            if (a > b) 
            {
		temp = a;
		a = b;
		b = temp;
            }
	}
		
            return b;
	}
    
    public static void main(String[] args)
    {
        System.out.println(gcd(24, 100)); // 4
    }
    
}
