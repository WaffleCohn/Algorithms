/**Solution to Iroh's Tea Cups
 * PWSH 2016 invitational
 * @author Aneesh Adhikari-Desai
 */


import java.io.*;
import java.util.*;

public class Cups {

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(new File("cups.in"));	//initialize file input
		int count = scn.nextInt();	//get number of test cases in file
		for(int i = 0; i < count; i++){	//repeat for all test cases
			
			int num = scn.nextInt();	//store number of stacks
			
			int avg = -1;	
			int sum = 0;	//initialize average, sum, and array variables
			int[] stacks = new int[num];	
			
			for(int k = 0; k < num; k++){
				stacks[k] = scn.nextInt();	//store stacks in an array
				sum += stacks[k];
			}
			
			avg = sum/num;	//find average height of stack, note integer division
							//ignores remainder
			
			System.out.print("Iroh needs to ");	//start output
			
			if(sum % num == 0){		//if stacks can be perfectly made
				
				int ans = 0;
				
				for(int n: stacks)
					ans += (int) (Math.abs(avg-n));	//determine number of cups out of place
				
				ans /= 2;	//avoid double counting both gaps AND extra cups, only find
							//cups that need to be moved
				
				System.out.println("move " + ans + " cups");	//finish output
				
			}
			else{
				
				int ans = sum%num;	//if not perfectly divisible, find remainder
				
				System.out.println("sell " + ans + " teacups to make it stack");	//finish output
			}
			
			
		}
		scn.close();	//close output stream, optional
			

	}

}
