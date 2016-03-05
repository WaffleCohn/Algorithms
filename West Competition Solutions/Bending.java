/**Solution to Pro Bending Placements
 * PWSH 2016 invitational
 * @author AN
 */

import java.util.*;
import java.io.*;

public class Bending {
	
	private static TreeSet<String> permutations;	//static variable to store 
													//answers to print
	
	public static void main(String[] args) throws IOException{
		Scanner scn = new Scanner(new File("bending.in"));	//access data from file
		
		int count = scn.nextInt();	//get number of test cases
		
		for(int i =  0; i < count; i++){
			int numTeams = scn.nextInt();	//get number of teams and awards
			int numAwards = scn.nextInt();
			scn.nextLine();	//flush buffer
			ArrayList<String> teams = new ArrayList<String>();//store team names
			
			for(int k = 0; k < numTeams; k++){
				teams.add(scn.nextLine());
			}
			permutations = new TreeSet<String>();	//initialize answer set
						
			findPermutations(teams, "", 0, numAwards, "");	//find all permutations of winning teams

			for(String s: permutations)	//display sorted set, treeset is inherently ordered
				System.out.println(s);
			System.out.println();	//blank line after each case, see prompt
		}
		
		scn.close();	//close output stream, not required

	}
	
	/** Method to recursively find all permutations of winners from list
	 * @param teams the list of teams to choose from
	 * @param taken the string of winners that have been taken
	 * @param numTaken the number of winners that have been taken
	 * @param toTake the total number of winners to take
	 * @param last the last winner added to the list (to be removed from teams)
	 */
	public static void findPermutations(ArrayList<String> teams, String taken, int numTaken, int toTake, String last){
		
		if(numTaken == toTake){	//if permutation complete
			taken = taken.replaceFirst(",", "");	//fix format
			permutations.add(taken.trim());	//add to answer list
		}
		ArrayList<String> clone = new ArrayList<String>(teams);	//clone to avoid aliasing
		clone.remove(last);	//remove previously inserted element from clone
			
		for(int i = 0; i < clone.size(); i++){	//go through and add each element to a potential answer
			findPermutations(clone, taken + ", " + clone.get(i), numTaken+1, toTake, clone.get(i));	
		}
		
	}

}
