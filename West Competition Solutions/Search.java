/**Solution to WordSearch
 * PWSH 2016 Invitational
 * @author Aneesh Adhikari-Desai
 */

import java.util.*;
import java.io.*;

public class Search {

	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(new File("search.in"));	//open file access
		
		int count = scn.nextInt();
		
		for(int i = 0; i < count; i++){
			char[][] grid = new char[scn.nextInt()][scn.nextInt()];	//initialize grid array
																	//with given dimensions
			
			scn.nextLine();		//flush buffer
			
			for(int k = 0; k < grid.length; k++)
				grid[k] = scn.nextLine().trim().toCharArray();	//create grid
			
			
			ArrayList<String> all = new ArrayList<String>();	//variable to store all possibilities
																//of rows and column answers
			for(int k = 0; k < grid.length; k++){		
				String toAdd = "";
				for(int j = 0; j < grid[k].length; j++){
					toAdd += grid[k][j];
				}
				all.add(toAdd); //add all rows to possibilities
				all.add(reverse(toAdd));
			}
			
			for(int k = 0; k < grid[0].length; k++){		
				String toAdd = "";
				for(int j = 0; j < grid.length; j++){
					toAdd += grid[j][k];	//note difference in order
				}
				all.add(toAdd); //add all columns to possibilities
				all.add(reverse(toAdd));
			}
			
			for(int k = 0; k < grid.length + grid[0].length - 2; k++){		
				String toAdd = "";
				for(int j = 0; j <= k; j++){
					if (j < grid.length && k-j < grid[0].length)
					toAdd += grid[j][k-j];
				}
				all.add(toAdd); //add some diagonals to possibilities
				all.add(reverse(toAdd));
			}

			//from the bottom left corner, go up, then go right once you hit the top row. Trust me it works.
			for(int c = 0, r = grid.length-1, outlet; c < grid[0].length; outlet = r > 0 ? r-- : c++)
			{		
				String toAdd = "";
				int q = r, w = c;
				for(;;) //while true
				{
					try{
						toAdd += ""+ grid[q++][w++]; //add the character in the bottom right direction and then move down-right
					}
					catch(Exception e){ //if we went out of bounds, die
						break;
					}
				}
				all.add(toAdd); //add some diagonals to possibilities
				all.add(reverse(toAdd));
			}
			
			int numQueries = scn.nextInt();
			scn.nextLine();		//flush buffer
			
			for(int k = 0; k < numQueries; k++){	//iterate through queries to find
				
				String query = scn.nextLine();	//store the query to find
				boolean found = false;	//initialize found boolean to false
				
				for(String s: all){	//check each possible string for the query
					if(s.contains(query)){
						System.out.println("I AM THE DRAGON OF THE WEST!");
						found = true;	//set found variable to true, then exit iteration
						break;
					}
				}
				
				if(!found)	//if query not found, print failure statement
					System.out.println("I have been betrayed.");
				
			}
			System.out.println();	//blank line
			
			
			
		}	
		
		
		
		scn.close();	//close input stream

	}
	/**
	 * Method to reverse a given string
	 * @param str the string to be reversed
	 * @return the reversed string
	 */
	public static String reverse(String str){
		return new StringBuilder(str).reverse().toString();
	}
	

}
