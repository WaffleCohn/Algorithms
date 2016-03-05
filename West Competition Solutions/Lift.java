/**Problem: Do you even lift?
 * @author Aneesh Adhikari-Desai
 */


import java.util.*;
import java.io.*;


public class Lift {

	private static ArrayList<Integer> coins;	//class variables to hold currency values  
	private static int minCoins;				//and the current best number of coins  
											
	public static void main(String[] args) throws IOException{
		Scanner scn = new Scanner(new File("lift.in"));
		int count = scn.nextInt();	//get the number of data sets
		scn.nextLine();			//flush the buffer
		
		for(int i = 0; i < count; i++){
			
			coins = new ArrayList<Integer>();	//initialize currency values variable
			minCoins = Integer.MAX_VALUE;		//and best number of coins
			
			String raw = scn.nextLine();		//grab the line with all coin values
			
			for(String s: raw.split(" ")){
				coins.add(Integer.parseInt(s));	//parse each token in the line into integers
			}
			
			Collections.reverse(coins);	//reverses order of list to descending, not required
										//significantly improves efficiency
			
				
			int value = Integer.parseInt(scn.nextLine());	//get needed currency value
															//scn.nextInt not used due to parsing issues
			
			for(Integer n: coins){
				findMin(value, 0, n);	//run minCoins for all coin values
			}
			
			System.out.println(minCoins);	//display minimum number of coins
		}
		
		
		scn.close();	//close input stream, optional

	}
	
	
	/**Recursive method to determine the minimum number of
	 * coins for the given value and assign it to the class variable
	 * @param valueLeft The remaining value that must be put into coins
	 * @param coinCount The current number of coins
	 * @param coin The value of the current selected coin
	 */
	
	public static void findMin(int valueLeft, int coinCount, int coin){
		
		coinCount++;	//increment the number of counted coins
		valueLeft-=coin;	//remove the value of the selected coin from the total remaining value
		
		
		
		if(valueLeft < 0 || coinCount >= minCoins){	//remove negative values or sets that
			return;									//cannot beat a previously found solution
		}
		
		if(valueLeft == 0 && coinCount < minCoins){
			minCoins = coinCount;	//if a potential solution has been found,
			return;					//check to see if it beats the current 'best'
		}
		
		else{
			//recurse for all potential coins
			for(Integer n: coins){
				findMin(valueLeft, coinCount, n);
			}
		}
		
	}

}
