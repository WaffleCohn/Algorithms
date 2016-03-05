/**Solution to Iroh's Menu
 * Plano West Invitational 2016
 * @author Aneesh Adhikari-Desai
 *
 */

import java.io.*;
import java.util.*;

public class Menu {

	public static void main(String[] args) throws IOException {
		
		Scanner scn = new Scanner(new File("menu.in"));		//open file for input
		
		while(scn.hasNextLine()){
			
			String line = scn.nextLine();
			line = line.replaceAll("[+-/\\*0-9A-Za-z]", "");	//remove all spaces and non delimiters
			line = line.replaceAll("\\s+", "");

			Deque<String> stack = new LinkedList<String>();	//initialize stack
			
			for(char c: line.toCharArray()){
				String s = ""+c;	//cast to string
				
				if(stack.isEmpty()){	//if empty, add the next character
					stack.push(s);
					continue;
				}
				
				if(s.equals(")") && stack.peek().equals("("))	
					stack.pop();
				else if(s.equals("]") && stack.peek().equals("["))	//if an end delimiter is found, check stack for its beginning 
					stack.pop();									//remove if found
				else if(s.equals("}") && stack.peek().equals("{"))
					stack.pop(); 
				else
					stack.push(s);	//otherwise add to stack
				
			}
			
			
			if(stack.isEmpty())	//if all delimiters are accounted for, stack is empty, print true
				System.out.println("True");
			else				//fail case
				System.out.println("False");

			
		}
		
		scn.close();	//close input stream, optional
	}

}
