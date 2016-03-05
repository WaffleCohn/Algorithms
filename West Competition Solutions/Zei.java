/**Solution to Professor Zei's Equations
 * PWSH 2016 Invitational
 * @author Aneesh Adhikari-Desai
 */
import java.util.*;
import java.io.*;

public class Zei {

	public static void main(String[] args) throws IOException{
		
		Scanner scn = new Scanner(new File("zei.in"));	//open file for input
			
		int count = scn.nextInt();	//get number of test cases
		scn.nextLine();		//flush buffer
		
		for(int i = 0; i < count; i++){
			
			Deque<Integer> stack = new LinkedList<Integer>();	//store operands in a stack until needed
			String[] splitLine = scn.nextLine().split("\\s+");	//split input at spaces
			
			for(int k = 0; k < splitLine.length; k++){
				
				if(splitLine[k].matches("[0-9]+")){
					stack.push(Integer.parseInt(splitLine[k]));		//if a number, add to stack
				}
				
				else{		//if an operator, get 2 operands from stack and evaluate (see method)
					int val = evaluate(stack.pop(), stack.pop(), splitLine[k]);	
					stack.push(val);	//add calculated value to stack
				}
				
			}
			System.out.println(stack.pop());	//display last number in stack, the answer
			
			
		}
		
		scn.close();	//close output stream, optional
		

	}
	
	/**
	 * Given two operands and an operator, evaluate expression 
	 * @param a first operand
	 * @param b second operand
	 * @param s operator
	 * @return the evaluated result of the expression
	 */
	public static int evaluate(int a, int b, String s){
		
		switch(s){	//check what operator
			case "+":	return b + a;
			case "-": 	return b - a;	//return appropriate value
			case "*":	return b * a;
			case "/":	return b / a;
		}
		
		return 0;	//error case, will never occur in context of problem
	}

}
