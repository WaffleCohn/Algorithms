/**Solution to Finding Appa and Momo 
 * PWSH 2016 invitational
 * @author Aneesh Adhikari-Desai
 */

import java.util.*;
import java.io.*;

public class Finding {

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(new File("finding.in"));	//access data from file
		
		String line = scn.nextLine();	//store line of data
		
		
		System.out.println("Appa: " + countPoss(line, "((", "))"));	//display answers
		System.out.println("Momo: " + countPoss(line, "[[", "]]"));//see methods below
		
		scn.close();	//close output stream, not required

	}
	
	/**Method to count the possibilities of locations for 
	 * Appa or Momo in a given string
	 * @param line the line to check for possibilities
	 * @param start the starting symbol (either Appa or Momo)
	 * @param end the ending symbol (either Appa or Momo)
	 * @return the number of possible locations for that character
	 */
	public static int countPoss(String line, String start, String end){
		int ans = 0;	//initialize count variable to 0
		int index = line.indexOf(start);	//initialize index to first index of start string
		while(index < line.length() && index != -1){	//loop while in bounds and string still found
			ans += countEnds(line, index, end); //add all end possibilites for that start location
												//to total counter
			index++;	//increment to avoid double-counting
			if(index == line.length())	//catch out of bounds case
				break;
			index = line.indexOf(start, index);	//reassign to next index
		}
		return ans;
	}
	
	/**Method to count the possibilities of end locations for 
	 * Appa or Momo in a given string starting from a particular index
	 * @param line the line to check for end possibilities
	 * @param startIndex the index from where to start searching
	 * @param find the symbol to search for
	 * @return the number of ending possibilities from that index
	 */
	
	public static int countEnds(String line, int startIndex, String find){
		int ans = 0;	//initialize count variable to 0
		int index = line.indexOf(find, startIndex);	//initialize index to first index of end string
		while(index < line.length() && index != -1){	//loop while in bounds and string still found
			ans++;	//increment number of found endpoints
			index++;	//increment to avoid double-counting
			if(index == line.length())	//catch out of bounds case
				break;
			index = line.indexOf(find, index);	//reassign to next index
		}
		return ans;
	}

}
