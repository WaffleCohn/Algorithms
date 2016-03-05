/**
 *Solution to Bad Signal
 *Plano West Invitational 2016
 *@author Aneesh Adhikari-Desai
 */
 
import java.io.*;
import java.util.*;

public class Signal {
	
	public static HashMap<char[], String> translate;
        
    public static void main(String[] args) throws IOException{
        Scanner scn = new Scanner(new File("signal.in"));	//open file for input
        
        int count = scn.nextInt();	//get number of test cases
        scn.nextLine();	//flush buffer
        
        for(int i = 0; i < count; i++){
        	
        	int codeLen = scn.nextInt();	//get length of codes, never used
        	int numCodes = scn.nextInt();	//number of predetermined codes
        	int maxWrong = scn.nextInt();	//max number of allowed errors, not used
        	
        	scn.nextLine();	//flush buffer
        	
        	translate = new HashMap<char[], String>();	//storage for codes that coorespond to translation, static var
        	
        	for(int k = 0; k < numCodes; k++){
        		String name = scn.next();
        		String code = scn.next();
        		
        		translate.put(code.toCharArray(), name);	//store code array and name in map
        		
        		scn.nextLine();	//flush buffer
        	}
        	
        	String line = scn.nextLine();	//grab line of codes
        	
        	Scanner split = new Scanner(line);	//split line of codes into individual codes
        	
        	while(split.hasNext()){
        		System.out.print(findClosest(split.next()) + " ");
        	}
        	System.out.println();
        	
        	
        	
        }
        
        
        scn.close();	//close input stream, optional
    }
    
    /**Method to find the best matching code for a given garbled code
     *@param s the garbled code
     *@return the best available translation
     */
    public static String findClosest(String s){
    	
    	int best = Integer.MAX_VALUE;	//keep track of best option's number of errors, start really high
    	String ans = "";
    	
    	char[] compare = s.toCharArray();	//convert to array for comparison
    	
    	for(char[] code: translate.keySet()){
    		
    		int currentErr = 0;
    		
    		for(int i = 0; i < compare.length; i++){
    			if(code[i] != compare[i])	//count number of wrong characters
    				currentErr++;
    		}
    		
    		if(currentErr < best){	//deterine which code has minimum errors and its translation
    			best = currentErr;
    			ans = translate.get(code);
    		}
    		
    	}
    	
    	return ans;	//return best translation
    	
    }
}
