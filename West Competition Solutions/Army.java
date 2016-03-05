/**Solution to Stack Overflow
 * PWSH 2016 invitational
 * @author Jiaming Chen
 */

import java.io.*;
import java.util.*;

public class Army {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("army.in")); //access data from file
		testCases:
		while (in.hasNextInt()){ //continue as long as there is more input
			int x = in.nextInt(); //get starting value and increment
			int n = in.nextInt();
			/* The scenario is represented by this modular equation:
			 * (3 - x) mod 2^32 = yn mod 2^32
			 * To get the value of y, we must multiply 3 - x by the
			 * modular multiplicative inverse of n. According to the
			 * Euler-Fermat theorem, the value of the multiplicative
			 * inverse of a number n mod m given that n and m are 
			 * coprime is congruent to n to the power of one less than 
			 * the totient function of m mod m. Since m is a power of 2, 
			 * the value of the totient function of m is always m/2.
			 */
			int z = 3-x; //calculate the value of the left side of the equation
			long m = 4294967296L; //value of modulus
			while ((n&1)==0) { //divide all numbers by the greatest common factor of n and m 
				               //since m is a power of 2 we can do this by repeatedly dividing by 2
				if ((z&1)!=0) { //there is no solution if z cannot be reduced alongside n and m
					System.out.println("Test case is trash.");
					continue testCases;
				}
				n>>=1;
				m>>=1;
				z>>=1;
			}
			int invn = exp(n, m/2-1); //calculate modular multiplicative inverse of n using Euler-Fermat theorem
			int y = z*invn; //multiply the left side of the equation by the multiplicative inverse of n to get y
			System.out.println(y>=0?y:y+m);
		}
	}
	
	//Binary exponentiation
	static int exp(int n, long p) {
		if (p == 1)
			return n;
		if ((p&1)==0)
			return exp(n*n, p/2);
		return n *exp(n*n, p/2);
	}

}
