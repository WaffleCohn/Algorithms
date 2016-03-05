/** Coldest Tea In Ba Sing Se Solution
 * @author Alexander Wong (Hat Guy)
 */

import java.util.*;
import java.io.*;

public class Tea
{
	public static void main(String args[]) throws IOException
    {
		Scanner scn = new Scanner(new File("tea.in")); //Scanner
		int it = scn.nextInt();
		
        //Iterate through datasets
        for(int x = 0; x < it; x++)
        { 
            //Convert to Celsius and find the distance from 85 degrees 
            double distance = (85.0 - (40.0 / 21.0) * (scn.nextDouble() - 7.5)); 
            
            //Scan the rate of heating
			double rate = scn.nextDouble();
            
            //Print the time. Time = Distance/Rate
			System.out.println((int) Math.ceil(distance/rate));
		}
	}
}
