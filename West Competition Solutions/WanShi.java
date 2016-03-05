/** WanShi's Last Theorem Solution
 * @author Alexander Wong (Hat Guy)
 */

import java.io.*;
import java.util.*;

public class WanShi
{
	public static void main(String args[])throws IOException
	{
		Scanner scn = new Scanner(new File("wanshi.in"));
		while(scn.hasNextLong()) //Iterate through the data
		{
			long x = scn.nextLong();
			long n = scn.nextLong();
			System.out.println((x^n + 1^n)^n);
			
			/*
				The reader must first understand that ^ is not exponentiation.
				^ is the bitwise OR. (Did I fool you?)
				
				The rest is trivial bitwise operation:
				
				If x^n + y^n = z^n
				Then if both sides are XOR'd by n
					(x^n + y^n)^n = z
				
				The lowest y > 0 is always 1. 
				So z = (x^n + 1^n)^n
			*/
		}
	}
}
