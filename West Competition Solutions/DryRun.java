/**Problem: Dry Run
 * @author Druv Patel
 */

import java.util.Scanner;
import java.io.*;

public class DryRun 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner scn = new Scanner (new File("dryrun.in"));	//Setup scanner
		int count = scn.nextInt();							//Get number of test cases
		
		for (int i = 0; i < count; i ++)
		{
			int a = scn.nextInt();
			System.out.print("l");	//print first 'l'
			
			for (int xy = 0; xy < a; xy++)
			{
				System.out.print("o");	//print given number of 'o's
			}
			
			System.out.println("l");	//print final 'l', end line
			
		}
		scn.close(); //close input stream, optional

	}

}
