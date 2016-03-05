
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

      	while (true){
          
          if(a < b)
          {
              int temp = a;
              a = b - a;
              b = temp;
          }
          int remainder = a%b;
          if(remainder == 0) return b;


          a = b;
          b = remainder;
      	}
     }

    public static void main(String[] args)
    {
        System.out.println(gcd(80, 55)); // 4
    }

}
