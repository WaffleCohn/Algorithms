/**Problem: Fire Nation Secrecy
 * @author Druv Patel
 */
import java.util.*;
import java.io.*;

public class Secrecy 
{

	public static void main(String[] args) throws IOException 
	{
		
		Scanner scn = new Scanner (new File("secrecy.in"));
		int count = scn.nextInt();	//get number of test cases
		
		for (int i = 0; i < count; i ++) //Iterate through the datasets.
		{
		
			String original = scn.next().toLowerCase();	//make everything lower case for now
			int length = original.length();
			int mid = length/2;	//set index for middle variable
				
			String first = original.substring(mid); //second half of word
			String letter = (first.charAt(0)+ "").toUpperCase();	//capitalize first letter in name
			first = letter + first.substring(1); 	//append capital to rest of word
			String second = "";	//declare variable for second half of name
			for (int a = mid-1; a >= 0; a--)	
			{
				
				second += original.charAt(a);	//add letters from the first half of the name
												//in reverse order
			}
			
			System.out.println(first+""+second);	//print final answer
			
		}
		
		scn.close();	//close input stream, optional
	}

}
