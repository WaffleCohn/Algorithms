/**Solution to The Blind Bandit
 * PWSH 2016 Invitational
 * @author Aneesh Adhikari-Desai
 */
import java.util.*;
import java.io.*;

public class Bandit {
	
	private static final int[] COINS = { 1, 5, 10, 25, 50, 100};	//final variable to hold coin values
	private static int[] countCoins;	//variable to hold counts of each coin per test case

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(new File("bandit.in"));
		
		int count = scn.nextInt();	//get the number of data sets
		
		for(int i = 0; i < count; i++){
			
			int value = scn.nextInt();	//get the value in currency to coin
			countCoins = new int[6];	//initialize count array
			
			for(int k = COINS.length-1; k >= 0; k--){	//traverse coins largest to smallest
				
				if(value >= COINS[k]){	//fit the largest coins as many times as possible
					countCoins[k] = value / COINS[k];
					value %= COINS[k];
				}
				
			}
			
			for(int c: countCoins){
				System.out.print(c + " ");	//display each counted coin
			}
			System.out.println();	//format to new line
			
		}
		
		
		scn.close();	//close input stream, optional
	}

}
