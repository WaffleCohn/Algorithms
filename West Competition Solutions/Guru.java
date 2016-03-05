/**Solution to Guru Pathik's Stand
 * Plano West Invitational 2016
 * @author Aneesh Adhikari-Desai
 *
 */

import java.io.*;
import java.util.*;

public class Guru {

	public static void main(String[] args) throws IOException {

		Scanner scn = new Scanner(new File("guru.in"));		//open file for input
		
		double onionPrice = scn.nextDouble();
		double bananaPrice = scn.nextDouble();
		double cupsPrice = scn.nextDouble();	//get all prices
		double onePrice = scn.nextDouble();
		
		int count = scn.nextInt();	//get number of days
		
		double balance = 0;
		
		for(int i = 0; i < count; i++){		//for each day, subtract costs and add revenue
			balance -= scn.nextInt() * onionPrice;
			balance -= scn.nextInt() * bananaPrice;
			balance -= scn.nextInt() * cupsPrice;
			balance += scn.nextInt() * onePrice;
		}
		
		if(balance < 0)
			System.out.print("FAILURE ");	//output success state
		else
			System.out.print("SUCCESS ");
		
		System.out.printf("%.2f", balance);	//print balance rounded to two decimal places
		
		scn.close();	//close input stream, optional
		

	}

}
